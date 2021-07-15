package com.exadel.sandbox.service.notification.impl;

import com.exadel.sandbox.dto.request.notification.SubscriberRequest;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.notification.SubscriberResponse;
import com.exadel.sandbox.dto.response.tag.TagResponse;
import com.exadel.sandbox.mail.MailUtil;
import com.exadel.sandbox.mappers.notification.SuscriberMapper;
import com.exadel.sandbox.model.notification.SubscriberEnum;
import com.exadel.sandbox.model.notification.Subscription;
import com.exadel.sandbox.model.notification.SubscriptionResult;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.category.CategoryRepository;
import com.exadel.sandbox.repository.notification.SubscriberRepository;
import com.exadel.sandbox.repository.tag.TagRepository;
import com.exadel.sandbox.repository.user.UserRepository;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.EventService;
import com.exadel.sandbox.service.UserService;
import com.exadel.sandbox.service.exceptions.DuplicateNameException;
import com.exadel.sandbox.service.notification.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository subscriberRepository;
    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final EventService eventService;
    private final UserService userService;
    private final SuscriberMapper suscriberMapper;
    private final MailUtil mailUtil;

    @Override
    public boolean saveSubscription(SubscriberRequest subscriberRequest, Long userId) {

        User user = userRepository.findById(userId).get();
        Subscription subscription = suscriberMapper.subscriberRequestToSubscription(subscriberRequest);

        if (subscriberRequest.isSubscribed()) {
            subscription.setUser(user);
            Subscription save=null;

            try {
                save = subscriberRepository.save(subscription);
            }catch (Exception ex){
                throw new DuplicateNameException("You have duplicate: " + subscription.getSubscriberType() + ": " + subscription.getSubscriberName());
            }

            if (save != null) {
                return true;
            }
        } else {
            Subscription subscriptionForDelete = subscriberRepository.findByUserIdAndSubscriberIdAndSubscriberType(
                    userId, subscription.getSubscriberId(), subscription.getSubscriberType());
            if (subscriptionForDelete != null) {
                subscriberRepository.delete(subscriptionForDelete);
                return true;
            }
        }

        return false;
    }

    @Override
    public List<SubscriberResponse> getAllVendorSubscriber(Long userId, SubscriberEnum subscriberType) {
        return getAllSubscribersByType(userId, subscriberType);
    }

    @Override
    public List<SubscriberResponse> getAllCategorySubscriber(Long userId, SubscriberEnum subscriberType) {
        return getAllSubscribersByType(userId, subscriberType);
    }

    @Override
    public List<SubscriberResponse> getAllTagSubscriber(Long userId, SubscriberEnum subscriberType) {
        return getAllSubscribersByType(userId, subscriberType);
    }

    @Override
    public boolean createEmailNotificationUsersBySubscription(Long eventId) {

        EventDetailsResponse eventById = eventService.getEventById(eventId);
        createNotificationUsers(eventById);

        return true;

    }

    private List<SubscriberResponse> getAllSubscribersByType(Long userId, SubscriberEnum subscriberType) {

        List<SubscriberResponse> checkedSubscribers = subscriberRepository.
                findAllByUserIdAndSubscriberTypeOrderBySubscriberNameAsc(userId, subscriberType.name()).stream()
                .map(suscriberMapper::subscriptionToSubscriberResponse)
                .collect(Collectors.toList());

        Set<SubscriptionResult> allAsSubscriptionResult = null;

        switch (subscriberType) {
            case VENDOR:
                allAsSubscriptionResult = vendorRepository.findAllVendorAsSubscriptionResult();
                break;
            case CATEGORY:
                allAsSubscriptionResult = categoryRepository.findAllCategoryAsSubscriptionResult();
                break;
            case TAG:
                allAsSubscriptionResult = tagRepository.findAllTagAsSubscriptionResult();
                break;
            default:
                allAsSubscriptionResult = null;
                break;
        }

        allAsSubscriptionResult.stream()
                .filter(subscriptionResult -> checkedSubscribers.stream().anyMatch(checked ->
                        subscriptionResult.getSubscriberId().equals(checked.getSubscriberId())))
                .forEach(subscriptionResult -> subscriptionResult.setSubscribed(true));

        return allAsSubscriptionResult.stream()
                .map(suscriberMapper::subscriptionResultToSubscriberResponse)
                .collect(Collectors.toList());
    }


    private void createNotificationUsers(EventDetailsResponse event) {


        Long categoryId = event.getCategoryId();
        Long vendorId = event.getVendorId();
        Set<Long> tags = getTagIds(event.getTags());

        Set<User> allUsersByCategoryFavorite = userService.findAllUsersByCategorySubscription(categoryId);
        Set<User> allUsersByVendorFavorite = userService.findAllUsersByVendorSubscription(vendorId);
        Set<User> allUsersByTagsFavorite = userService.findAllUsersByTagsSubscription(tags);

        Set<User> totalSetOfUsers = unionSetsOfUsers(allUsersByCategoryFavorite, allUsersByVendorFavorite, allUsersByTagsFavorite);

        totalSetOfUsers.stream()
                .forEach(user -> mailUtil.sendFavoriteMessage(user.getEmail(), String.valueOf(event.getId())));

    }

    private Set<Long> getTagIds(Set<TagResponse> tags) {
        return tags.stream()
                .map(TagResponse::getId)
                .collect(Collectors.toSet());
    }

    private Set<User> unionSetsOfUsers(Set<User>... userSets) {
        Set<User> totalUserSet = new HashSet<>();
        for (Set<User> userSet : userSets) {
            totalUserSet.addAll(userSet);
        }
        return totalUserSet;
    }
}
