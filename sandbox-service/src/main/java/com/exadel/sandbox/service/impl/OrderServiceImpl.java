package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.event.EventResponseFoOrders;
import com.exadel.sandbox.mail.MailUtil;
import com.exadel.sandbox.mappers.event.EventMapper;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.user.UserOrderRepository;
import com.exadel.sandbox.repository.user.UserRepository;
import com.exadel.sandbox.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final EventRepository eventRepository;
    private final UserOrderRepository userOrderRepository;
    private final UserRepository userRepository;

    private final EventMapper eventMapper;
    private final MailUtil mailUtil;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a, dd-MM-yyyy");

    @Override
    public String saveEventToOrder(Long userId, Long eventId, String email) {
        var event = verifyEventId(eventId);
        mailUtil.sendSimpleMessage(email,
                userRepository.getUsername(userId),
                event.getVendor().getName(),
                formatter.format(event.getDateEnd()));
        userOrderRepository.insertIntoUserOrder(eventId, userId, LocalDateTime.now());
        return "Event successfully saved to User Order ";
    }

    @Override
    public String removeEventFromOrder(Long eventId, Long userId) {
        var exist = eventRepository.getOneEventsFromUserOrder(eventId, userId);
        if (exist == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Not Found. EventId: " + eventId + " in User Order");

        userOrderRepository.deleteFromUserOrder(eventId, userId);
        return "Event successfully removed from User Order ";
    }

    @Override
    public List<EventResponseFoOrders> getAllFromOrder(Long userId) {
               return eventMapper.eventToEventResponseFoOrderWithDate(
                       userOrderRepository.getAllEventsFromUserOrderTest(userId)).stream()
                       .sorted(Comparator.comparing(EventResponseFoOrders::getGettingDate).reversed())
                       .collect(Collectors.toList());

    }

    private Event verifyEventId(Long eventId) {
        if (eventId <= 0) {
            throw new IllegalArgumentException("Id is not correct");
        }

        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Not Found. EventId: " + eventId + " in User Order"));

    }

}
