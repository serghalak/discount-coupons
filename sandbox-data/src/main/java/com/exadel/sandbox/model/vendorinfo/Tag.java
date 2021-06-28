package com.exadel.sandbox.model.vendorinfo;

import com.exadel.sandbox.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "category")
@EqualsAndHashCode(callSuper = false, exclude = "category")
public class Tag extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}