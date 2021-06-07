package com.exadel.sandbox.model.location;

import com.exadel.sandbox.model.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "location")
public class Location extends BaseEntity {

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

//    @ManyToMany(mappedBy = "locations")
//    private Set<Event> events=new HashSet<>();

//    @OneToMany(mappedBy = "location")
//    private Set<User>users=new HashSet<>();

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double lotitude) {
        this.latitude = lotitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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


//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
}
