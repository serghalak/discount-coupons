package com.exadel.sandbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@SqlResultSetMapping(
//        name = "LocalFilterMapping",
//        classes = @ConstructorResult(
//                targetClass = LocationFilter.class,
//                columns = {
//                        @ColumnResult(name = "countryId",type = Long.class),
//                        @ColumnResult(name = "countryName"),
//                        @ColumnResult(name = "cityId", type=Long.class),
//                        @ColumnResult(name = "cityName")}))
 public class LocationFilter {
    long countryId;
    String countryName;
    long cityId;
    String cityName;
}
