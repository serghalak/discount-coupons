package com.exadel.sandbox.model.vendorinfo;

import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="event")
public class Event extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name = "date_begin")
    private LocalDate dateBegin;

    @Column(name="date_end")
    private LocalDate dateEnd;

    @Column(name="total_count")
    private int totalCount;

//    @Column("discount")
//    private int discount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="limitation")
    private int limitation;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(nullable = false)
    private boolean isOnline;

    @ManyToMany
    @JoinTable(name = "event_product"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products=new HashSet<>();

    @ManyToMany
    @JoinTable(name = "event_location"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Set<Location> locations=new HashSet<>();

    @ManyToMany
    @JoinTable(name = "saved_event"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSavedEvents =new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_order"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userOrders=new HashSet<>();

    @ManyToMany
    @JoinTable(name = "feedback"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userFeedbacks=new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getLimitation() {
        return limitation;
    }

    public void setLimitation(int limitation) {
        this.limitation = limitation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<User> getUserSavedEvents() {
        return userSavedEvents;
    }

    public void setUserSavedEvents(Set<User> userEvents) {
        this.userSavedEvents = userEvents;
    }

    public Set<User> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(Set<User> userOrders) {
        this.userOrders = userOrders;
    }

    public Set<User> getUserFeedbacks() {
        return userFeedbacks;
    }

    public void setUserFeedbacks(Set<User> userFeedbacks) {
        this.userFeedbacks = userFeedbacks;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
