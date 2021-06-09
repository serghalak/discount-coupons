package com.exadel.sandbox.model.user;

import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.vendorinfo.Event;
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
@ToString(exclude = {"savedEvents", "usersOrder"})
@EqualsAndHashCode(callSuper = false, exclude = {"savedEvents", "usersOrder"})
public class User extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;



    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @Builder.Default
    @Column(name = "enable")
    private Boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany
    @JoinTable(name = "saved_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> savedEvents = new HashSet<>();

    @OneToMany
    @JoinTable(name = "user_order",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> usersOrder = new HashSet<>();

}
