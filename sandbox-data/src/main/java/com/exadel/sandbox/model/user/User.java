package com.exadel.sandbox.model.user;

import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"location","savedEvents", "usersOrder"})
@EqualsAndHashCode(callSuper = false, exclude = {"location","savedEvents", "usersOrder"})
public class User extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enable")
    private boolean isEnabled;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role", columnDefinition = "ENUM('USER', 'MODERATOR', 'ADMIN')")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "location_id")
     private Location location;

    @ManyToMany(mappedBy = "userSavedEvents")
    @JsonIgnore
    private Set<Event> savedEvents = new HashSet<>();

    @OneToMany
    @JoinTable(name = "user_order",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    @JsonIgnore
    private Set<Event> usersOrder = new HashSet<>();

}
