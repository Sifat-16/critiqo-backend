package com.critiqo.critiqo_backend.service.user;

import com.critiqo.critiqo_backend.model.user.User;
import com.critiqo.critiqo_backend.request.user.CreateUserRequest;

import java.util.Optional;

public interface UserService {
    User createUser(CreateUserRequest createUserRequest);
    Optional<User> findUserByEmail(String email);
}
