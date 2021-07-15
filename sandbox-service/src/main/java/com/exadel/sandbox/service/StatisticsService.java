package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.response.statistics.OrderStatisticsResponse;
import com.exadel.sandbox.dto.response.statistics.SavedStatisticResponse;

import java.time.LocalDate;
import java.util.List;

public interface StatisticsService {

    List<OrderStatisticsResponse> getAllEventsFromUserOrderForPeriod(
            LocalDate dateBegin, LocalDate dateEnd, Integer number);

    List<SavedStatisticResponse> getAllEventsFromUserSavedForPeriod(
            LocalDate dateBegin, LocalDate dateEnd, Integer number);
}
