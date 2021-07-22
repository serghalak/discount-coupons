package com.exadel.sandbox.model.vendorinfo;

import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.LocationFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"events", "tags"})
@EqualsAndHashCode(callSuper = false, exclude = {"events", "tags"})
public class Category extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "category",cascade=CascadeType.ALL)
    private Set<Tag> tags = new HashSet<>();

}
