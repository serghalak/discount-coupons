package com.exadel.sandbox.controllers;

import com.exadel.sandbox.service.impl.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/favorite")
    public ResponseEntity<?> getBestEventsByFavoriteParam(
            @RequestParam(name = "from", required = false) LocalDateTime from,
            @RequestParam(name = "to", required = false) LocalDateTime to
    ) {
        return new ResponseEntity<>(statisticService.getBestEventsByFavoriteParam(from, to), HttpStatus.OK);
    }

}
