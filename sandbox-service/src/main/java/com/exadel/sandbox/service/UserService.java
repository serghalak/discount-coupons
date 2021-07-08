package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {

    List<User> findAll();

    UserResponse findByName(final String name);

    Set<User> findAllUsersByVendorFavorite(Long vendorId);

    Set<User> findAllUsersByCategoryFavorite(Long categoryId);

    Set<User> findAllUsersByTagsFavorite(Set<Long>tagIds);
}
