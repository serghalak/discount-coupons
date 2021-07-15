package com.exadel.sandbox.model.notification;

import com.exadel.sandbox.model.BaseEntity;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.model.vendorinfo.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subscription",
        uniqueConstraints = {@UniqueConstraint(columnNames={"subscriber_type", "subscriber_id", "user_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
@EqualsAndHashCode(callSuper = false, exclude = {"user"})
public class Subscription extends BaseEntity{

        @Column(name = "subscriber_type")
        private String subscriberType;

        @Column(name = "subscriber_id")
        private Long subscriberId;

        @Column(name = "subscriber_name")
        private String subscriberName;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

}
