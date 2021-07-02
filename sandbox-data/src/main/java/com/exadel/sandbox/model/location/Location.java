package com.exadel.sandbox.model.location;

import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.LocationFilter;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "city")
@EqualsAndHashCode(callSuper = false, exclude = "city")
@SqlResultSetMapping(
        name = "LocalFilterMapping",
        classes = @ConstructorResult(
                targetClass = LocationFilter.class,
                columns = {
                        @ColumnResult(name = "countryId",type = Long.class),
                        @ColumnResult(name = "countryName"),
                        @ColumnResult(name = "cityId", type=Long.class),
                        @ColumnResult(name = "cityName")}))
public class Location extends BaseEntity {

    @NonNull
    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
