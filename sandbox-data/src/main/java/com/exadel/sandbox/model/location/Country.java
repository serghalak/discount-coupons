package com.exadel.sandbox.model.location;

import com.exadel.sandbox.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @JsonIgnore
    private Set<City> cities;

}
