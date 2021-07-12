package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.event.EventResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatisticService {
    public List<EventResponse> getBestEventsByFavoriteParam(LocalDateTime from, LocalDateTime to) {
        return null;
    }
}
