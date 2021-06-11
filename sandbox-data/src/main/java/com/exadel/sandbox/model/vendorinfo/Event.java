package com.exadel.sandbox.model.vendorinfo;

import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"products", "locations", "userSavedEvents", "userOrders", "userFeedbacks"})
@EqualsAndHashCode(callSuper = false, exclude = {"products", "locations", "userSavedEvents", "userOrders", "userFeedbacks"})
public class Event extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date_begin")
    private LocalDate dateBegin;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "total_count")
    private int totalCount;

    @Column(name = "discount")
    private int discount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "limitation")
    private int limitation;

    @Column(name = "email")
    private String email;

    @Column(name = "status", columnDefinition = "ENUM('NEW', 'ACTIVE', 'EXPIRED')")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NonNull
    @Column
    private boolean isOnline;


    @ManyToMany
    @JoinTable(name = "event_product"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "event_location"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Set<Location> locations = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "saved_event"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSavedEvents = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_order"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userOrders = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "feedback"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userFeedbacks = new HashSet<>();

}
