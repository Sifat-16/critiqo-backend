package com.critiqo.critiqo_backend.service.auth;

import com.critiqo.critiqo_backend.model.user.User;
import com.critiqo.critiqo_backend.repository.user.UserRepository;
import com.critiqo.critiqo_backend.request.auth.AuthenticationRequest;
import com.critiqo.critiqo_backend.response.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @Override
    public AuthResponse authenticate(AuthenticationRequest authenticationRequest) {

        Optional<User> user = userRepository.findByEmail(authenticationRequest.getEmail());


        if(user.isPresent()){

        }else{

        }

        return null;
    }
}
