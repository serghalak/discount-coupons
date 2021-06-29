package com.exadel.sandbox.dto.request.filter;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequest {

    Long locationId;
    Boolean isCountry;
    Set<Long> categories;
    Set<Long>vendors;
    Set<Long>tags;
    String main;
}
