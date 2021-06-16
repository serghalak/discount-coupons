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
@EqualsAndHashCode(callSuper = false, exclude = {"locations", "country"})
public class City extends BaseEntity {

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @JsonIgnore
    private Country country;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private Set<Location> locations = new HashSet<>();

}
