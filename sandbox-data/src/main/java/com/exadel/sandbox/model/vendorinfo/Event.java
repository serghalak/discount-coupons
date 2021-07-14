package com.exadel.sandbox.model.vendorinfo;

import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"category", "locations", "userSavedEvents", "userOrders", "userFeedbacks", "tags"})
@EqualsAndHashCode(callSuper = false, exclude = {"category", "locations", "userSavedEvents", "userOrders",
        "userFeedbacks", "tags"})
public class Event extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "date_begin")
    private LocalDateTime dateBegin;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

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

    @Column(name = "status", columnDefinition = "ENUM('NEW', 'COMING_SOON', 'ACTIVE', 'EXPIRED', 'PERPETUAL')")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NonNull
    @Column
    private boolean isOnline;

    @ManyToMany
    @JoinTable(name = "event_location",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    @JsonIgnore
    private Set<Location> locations = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "saved_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> userSavedEvents = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_order",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> userOrders = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "feedback",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> userFeedbacks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @JsonIgnore
    private Vendor vendor;

    @ManyToMany
    @JoinTable(name = "event_tag",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
}
