package com.exadel.sandbox.service.notification.impl;

import com.exadel.sandbox.dto.request.notification.SubscriberRequest;
import com.exadel.sandbox.dto.response.notification.SubscriberResponse;
import com.exadel.sandbox.mappers.notification.SuscriberMapper;
import com.exadel.sandbox.model.notification.SubscriberEnum;
import com.exadel.sandbox.model.notification.Subscription;
import com.exadel.sandbox.model.notification.SubscriptionResult;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.repository.category.CategoryRepository;
import com.exadel.sandbox.repository.notification.SubscriberRepository;
import com.exadel.sandbox.repository.tag.TagRepository;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.notification.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final SuscriberMapper suscriberMapper;

    @Override
    public boolean saveSubscription(SubscriberRequest subscriberRequest, Long userId) {

        User user = userRepository.findById(userId).get();
        Subscription subscription = suscriberMapper.subscriberRequestToSubscription(subscriberRequest);

        if (subscriberRequest.isSubscribed()) {
            subscription.setUser(user);
            Subscription save = subscriberRepository.save(subscription);
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

    private List<SubscriberResponse> getAllSubscribersByType(Long userId, SubscriberEnum subscriberType) {

        List<SubscriberResponse> checkedSubscribers = subscriberRepository.
                findAllByUserIdAndSubscriberTypeOrderBySubscriberNameAsc(userId, subscriberType.name()).stream()
                .map(suscriberMapper::subscriptionToSubscriberResponse)
                .collect(Collectors.toList());

        Set<SubscriptionResult> allAsSubscriptionResult=null;

        switch (subscriberType){
            case VENDOR:
                allAsSubscriptionResult=vendorRepository.findAllVendorAsSubscriptionResult();
                break;
            case CATEGORY:
                allAsSubscriptionResult=categoryRepository.findAllCategoryAsSubscriptionResult();
                break;
            case TAG:
                allAsSubscriptionResult=tagRepository.findAllTagAsSubscriptionResult();
                break;
            default:
                allAsSubscriptionResult=null;
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
}
