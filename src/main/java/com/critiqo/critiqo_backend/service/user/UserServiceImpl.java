package com.critiqo.critiqo_backend.service.user;

import com.critiqo.critiqo_backend.model.user.User;
import com.critiqo.critiqo_backend.repository.user.UserRepository;
import com.critiqo.critiqo_backend.request.user.CreateUserRequest;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .email(createUserRequest.getEmail())
                .userRole(createUserRequest.getUserRole())
                .password(generateRandomPassword(12))
                .build();
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        String password = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, length);
        return bCryptPasswordEncoder.encode(password);
    }
}
