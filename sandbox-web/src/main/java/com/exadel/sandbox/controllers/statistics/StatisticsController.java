package com.exadel.sandbox.controllers.statistics;

import com.exadel.sandbox.service.StatisticsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/")
@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("statistics/byOrders")
    public ResponseEntity<?> getStatisticsForOrders(
            @RequestParam(name = "dateBegin", required = true) String dateBegin,
            @RequestParam(name = "dateEnd", required = true) String dateEnd,
            @RequestParam(name = "number", required = false, defaultValue = "5") Integer number){
        LocalDate localDateBegin = LocalDate.parse(dateBegin);
        LocalDate localDateEnd = LocalDate.parse(dateEnd);

        return ResponseEntity.ok()
                .body(statisticsService.
                        getAllEventsFromUserOrderForPeriod(localDateBegin,localDateEnd,number));
    }

    @GetMapping("statistics/byFavorites")
    public ResponseEntity<?> getStatisticsForSaved(
            @RequestParam(name = "dateBegin", required = true) String dateBegin,
            @RequestParam(name = "dateEnd", required = true) String dateEnd,
            @RequestParam(name = "number", required = false, defaultValue = "5") Integer number){
        LocalDate localDateBegin = LocalDate.parse(dateBegin);
        LocalDate localDateEnd = LocalDate.parse(dateEnd);

        return ResponseEntity.ok()
                .body(statisticsService.
                        getAllEventsFromUserSavedForPeriod(localDateBegin,localDateEnd,number));
    }
}
