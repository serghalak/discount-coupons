package com.exadel.sandbox.mappers.user;

import com.exadel.sandbox.dto.request.user.UserRequest;
import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.model.user.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class UserMapper {

    private ModelMapper mapper;

    public UserResponse userToUserResponse(User user) {
        return Objects.isNull(user) ? null : mapper.map(user, UserResponse.class);
    }

    public User userRequestToUser(UserRequest userRequest) {
        return Objects.isNull(userRequest) ? null : mapper.map(userRequest, User.class);
    }
}
