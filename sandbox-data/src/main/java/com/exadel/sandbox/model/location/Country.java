package com.exadel.sandbox.model.location;

import com.exadel.sandbox.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "cities")
@EqualsAndHashCode(callSuper = false, exclude = "cities")
public class Country extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<City> cities;

}
