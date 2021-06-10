package com.exadel.sandbox.model.location;

import com.exadel.sandbox.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"locations", "country"})
@EqualsAndHashCode(callSuper = false, exclude = "locations")
public class City extends BaseEntity {

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private Set<Location> locations = new HashSet<>();

}
