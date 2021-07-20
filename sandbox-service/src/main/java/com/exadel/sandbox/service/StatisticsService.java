package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.response.statistics.StatisticsResponse;

import java.time.LocalDate;
import java.util.List;

public interface StatisticsService {

    List<StatisticsResponse> getAllEventsFromUserOrderForPeriod(
            LocalDate dateBegin, LocalDate dateEnd, Integer number);

    List<StatisticsResponse> getAllEventsFromUserSavedForPeriod(
            LocalDate dateBegin, LocalDate dateEnd, Integer number);

    List<StatisticsResponse> getAllEventsFromViewedForPeriod(
            LocalDate dateBegin, LocalDate dateEnd, Integer number);
}
