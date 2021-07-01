package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.request.filter.FilterRequest;
import com.exadel.sandbox.dto.response.filter.FilterResponse;
import com.exadel.sandbox.service.filter.FilterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/")
@RestController
public class FilterController {

    private FilterService filterService;

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = {"filtering"})
    public FilterResponse getFilterList(@RequestBody FilterRequest filterRequest){

        log.debug(">>>>>filtering "+ filterRequest);
        return filterService.getFilterResponse(filterRequest);

    }
}
