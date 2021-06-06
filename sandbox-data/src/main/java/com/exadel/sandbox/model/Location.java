package com.exadel.sandbox.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "location")
public class Location extends BaseEntity{

    @Column(name="lotitude")
    private BigDecimal lotitude;
    @Column(name="longitude")
    private BigDecimal longitude;
    @Column(name="street")
    private String street;
    @Column(name="number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

//    @ManyToMany(mappedBy = "locations")
//    private Set<Event> events=new HashSet<>();

    @OneToMany(mappedBy = "location")
    private Set<User>users=new HashSet<>();

    public BigDecimal getLotitude() {
        return lotitude;
    }

    public void setLotitude(BigDecimal lotitude) {
        this.lotitude = lotitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }



    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
