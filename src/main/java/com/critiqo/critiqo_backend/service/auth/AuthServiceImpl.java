package com.critiqo.critiqo_backend.service.auth;

import com.critiqo.critiqo_backend.model.token.AuthToken;
import com.critiqo.critiqo_backend.model.user.User;
import com.critiqo.critiqo_backend.model.user.UserRole;
import com.critiqo.critiqo_backend.repository.user.UserRepository;
import com.critiqo.critiqo_backend.request.auth.AuthenticationRequest;
import com.critiqo.critiqo_backend.request.user.CreateUserRequest;
import com.critiqo.critiqo_backend.response.auth.AuthResponse;
import com.critiqo.critiqo_backend.response.auth.AuthResponseType;
import com.critiqo.critiqo_backend.service.jwt.JwtService;
import com.critiqo.critiqo_backend.service.token.TokenService;
import com.critiqo.critiqo_backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public AuthResponse authenticate(AuthenticationRequest authenticationRequest) {

        AuthResponse authResponse;

        Optional<User> user = userService.findUserByEmail(authenticationRequest.getEmail());

        AuthToken token = tokenService.generateAuthToken(authenticationRequest.getEmail());

        if(user.isPresent()){
            //Login flow
            authResponse = AuthResponse.builder()
                    .email(authenticationRequest.getEmail())
                    .authResponseType(AuthResponseType.LOGIN.name())
                    .authToken(token)
                    .build();
        }else{
            //Signup flow
            CreateUserRequest createUserRequest = CreateUserRequest.builder()
                    .email(authenticationRequest.getEmail())
                    .userRole(UserRole.USER)
                    .build();
            User newUser = userService.createUser(createUserRequest);
            authResponse = AuthResponse.builder()
                    .email(authenticationRequest.getEmail())
                    .userName(authenticationRequest.getName())
                    .authResponseType(AuthResponseType.SIGNUP.name())
                    .authToken(token)
                    .build();

        }

        return authResponse;
    }
}
