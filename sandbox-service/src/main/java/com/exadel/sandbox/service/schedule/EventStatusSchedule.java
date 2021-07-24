package com.exadel.sandbox.service.schedule;

import com.exadel.sandbox.repository.event.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class EventStatusSchedule {

    private final EventRepository eventRepository;

    @Scheduled(cron = "${spring.schedule.cron}")
    public void updateEventStatus(){
        eventRepository.updateEventStatusScheduled("EXPIRED", LocalDateTime.now());
    }
}
