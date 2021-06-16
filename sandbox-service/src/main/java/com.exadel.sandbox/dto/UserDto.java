package com.exadel.sandbox.dto;

import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class UserDto {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private Location location;
}
