package com.exadel.sandbox.model.vendorinfo;

import com.exadel.sandbox.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"category", "events"})
@EqualsAndHashCode(callSuper = false, exclude = {"category", "events"})
public class Tag extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "tags")
    private Set<Event> events = new HashSet<>();

}