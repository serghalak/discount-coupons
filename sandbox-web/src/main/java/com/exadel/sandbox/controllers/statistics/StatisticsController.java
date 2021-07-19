package com.exadel.sandbox.controllers.statistics;

import com.exadel.sandbox.service.StatisticsService;
import com.exadel.sandbox.service.statistics.StatisticsReportToExcel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/statistics/")
@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticsReportToExcel report;

    @GetMapping("byOrders")
    public ResponseEntity<?> getStatisticsForOrders(
            @RequestParam(name = "dateBegin", required = true) String dateBegin,
            @RequestParam(name = "dateEnd", required = true) String dateEnd,
            @RequestParam(name = "number", required = false, defaultValue = "5") Integer number) {
        LocalDate localDateBegin = LocalDate.parse(dateBegin);
        LocalDate localDateEnd = LocalDate.parse(dateEnd);

        return ResponseEntity.ok()
                .body(statisticsService.
                        getAllEventsFromUserOrderForPeriod(localDateBegin, localDateEnd, number));
    }

    @GetMapping("byFavorites")
    public ResponseEntity<?> getStatisticsForSaved(
            @RequestParam(name = "dateBegin", required = true) String dateBegin,
            @RequestParam(name = "dateEnd", required = true) String dateEnd,
            @RequestParam(name = "number", required = false, defaultValue = "5") Integer number) {
        LocalDate localDateBegin = LocalDate.parse(dateBegin);
        LocalDate localDateEnd = LocalDate.parse(dateEnd);

        return ResponseEntity.ok()
                .body(statisticsService.
                        getAllEventsFromUserSavedForPeriod(localDateBegin, localDateEnd, number));
    }

    @GetMapping("byViewed")
    public ResponseEntity<?> getStatisticsForViewed(
            @RequestParam(name = "dateBegin") String dateBegin,
            @RequestParam(name = "dateEnd") String dateEnd,
            @RequestParam(name = "number", required = false, defaultValue = "5") Integer number) {
        LocalDate localDateBegin = LocalDate.parse(dateBegin);
        LocalDate localDateEnd = LocalDate.parse(dateEnd);

        return ResponseEntity.ok()
                .body(statisticsService.
                        getAllEventsFromViewedForPeriod(localDateBegin, localDateEnd, number));
    }

    @GetMapping("downloadTemplate")
    public ResponseEntity<ByteArrayResource> downloadTemplate(
            @RequestParam(name = "dateBegin") String dateBegin,
            @RequestParam(name = "dateEnd") String dateEnd) {

        LocalDate localDateBegin = LocalDate.parse(dateBegin);
        LocalDate localDateEnd = LocalDate.parse(dateEnd);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "force-download"));
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductTemplate.xls");
            new ResponseEntity<>(
                    new ByteArrayResource(report.export(localDateBegin, localDateEnd).toByteArray()),
                    headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

}
