package com.exadel.sandbox.model;

import com.exadel.sandbox.model.vendorinfo.Event;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "statistic")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "event")
@EqualsAndHashCode(callSuper = false)
public class EventStatistic extends BaseEntity {

    @OneToOne(mappedBy = "eventStatistic")
    private Event event;

    @Column(name = "order_count")
    private Long orderCount;

    @Column(name = "favorite_count")
    private Long favoriteCount;

    @Column(name = "viewed_count")
    private Long viewedCount;
}
