package com.exadel.sandbox.model.user;

import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany
    @JoinTable(name = "saved_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    @JsonIgnore
    private Set<Event> savedEvents = new HashSet<>();

    @OneToMany
    @JoinTable(name = "user_order",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    @JsonIgnore
    private Set<Event> usersOrder = new HashSet<>();

}
