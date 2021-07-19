package com.exadel.sandbox.service.statistics;

import com.exadel.sandbox.dto.response.statistics.StatisticsResponse;
import com.exadel.sandbox.repository.statistics.OrderStatisticsProjection;
import com.exadel.sandbox.repository.statistics.SavedStatisticProjection;
import com.exadel.sandbox.repository.statistics.ViewedStatisticProjection;
import com.exadel.sandbox.repository.user.UserOrderRepository;
import com.exadel.sandbox.repository.user.UserSavedRepository;
import com.exadel.sandbox.repository.user.UserViewedEventRepository;
import com.exadel.sandbox.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final UserOrderRepository userOrderRepository;
    private final UserSavedRepository userSavedRepository;
    private final UserViewedEventRepository viewedEventRepository;

    @Override
    public List<StatisticsResponse> getAllEventsFromUserOrderForPeriod(
            LocalDate dateBegin, LocalDate dateEnd, Integer number) {
        List<OrderStatisticsProjection> statisticsProjections = userOrderRepository.
                getAllEventsFromUserOrderForPeriod(dateBegin, dateEnd);

        List<StatisticsResponse> responseList = converterOrders(statisticsProjections);
        number = checkSizeAndNumber(responseList.size(), number);

        return responseList.subList(0, number);
    }

    @Override
    public List<StatisticsResponse> getAllEventsFromUserSavedForPeriod(
            LocalDate dateBegin, LocalDate dateEnd, Integer number) {
        List<SavedStatisticProjection> statisticsProjections = userSavedRepository.
                getAllEventsFromUserSavedForPeriod(dateBegin, dateEnd);
        List<StatisticsResponse> responsesList = converterSaved(statisticsProjections);
        number = checkSizeAndNumber(responsesList.size(), number);

        return responsesList.subList(0, number);
    }

    @Override
    public List<StatisticsResponse> getAllEventsFromViewedForPeriod(
            LocalDate dateBegin, LocalDate dateEnd, Integer number) {
        List<ViewedStatisticProjection> statisticsProjections = viewedEventRepository.
                getAllEventsFromViewedForPeriod(dateBegin, dateEnd);
        List<StatisticsResponse> responsesList = converterViewed(statisticsProjections);
        number = checkSizeAndNumber(responsesList.size(), number);

        return responsesList.subList(0, number);
    }

    private List<StatisticsResponse> converterOrders(List<OrderStatisticsProjection> statisticsProjections) {

        Map<Long, List<OrderStatisticsProjection>> responseMap = statisticsProjections.stream()
                .collect(Collectors.groupingBy(OrderStatisticsProjection::getEventId));

        return responseMap.values().stream()
                .map(orderStatisticsProjections -> new StatisticsResponse(
                        orderStatisticsProjections.get(0).getEventId(),
                        orderStatisticsProjections.get(0).getEventDescription(),
                        orderStatisticsProjections.get(0).getVendorName(),
                        orderStatisticsProjections.size()))
                .sorted(Comparator.comparing(StatisticsResponse::getCount).reversed())
                .collect(Collectors.toList());
    }

    private List<StatisticsResponse> converterSaved(List<SavedStatisticProjection> statisticsProjections) {

        Map<Long, List<SavedStatisticProjection>> responseMap = statisticsProjections.stream()
                .collect(Collectors.groupingBy(SavedStatisticProjection::getEventId));

        return responseMap.values().stream()
                .map(savedStatisticProjections -> new StatisticsResponse(
                        savedStatisticProjections.get(0).getEventId(),
                        savedStatisticProjections.get(0).getEventDescription(),
                        savedStatisticProjections.get(0).getVendorName(),
                        savedStatisticProjections.size()))
                .sorted(Comparator.comparing(StatisticsResponse::getCount).reversed())
                .collect(Collectors.toList());
    }

    private List<StatisticsResponse> converterViewed(List<ViewedStatisticProjection> statisticsProjections) {

        Map<Long, List<ViewedStatisticProjection>> responseMap = statisticsProjections.stream()
                .collect(Collectors.groupingBy(ViewedStatisticProjection::getEventId));

        return responseMap.values().stream()
                .map(viewedStatisticProjections -> new StatisticsResponse(
                        viewedStatisticProjections.get(0).getEventId(),
                        viewedStatisticProjections.get(0).getEventDescription(),
                        viewedStatisticProjections.get(0).getVendorName(),
                        viewedStatisticProjections.size()))
                .sorted(Comparator.comparing(StatisticsResponse::getCount).reversed())
                .collect(Collectors.toList());
    }

    private int checkSizeAndNumber(int size, int number) {
        return size <= number ? size : number;
    }

}
