package com.exadel.sandbox.dto.response.user;

import com.exadel.sandbox.dto.response.location.LocationResponse;
import com.exadel.sandbox.model.user.Role;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class UserResponse {

    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private LocationResponse location;

}