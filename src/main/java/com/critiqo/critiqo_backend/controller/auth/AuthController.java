package com.critiqo.critiqo_backend.controller.auth;

import com.critiqo.critiqo_backend.model.profile.Profile;
import com.critiqo.critiqo_backend.request.auth.AuthenticationRequest;
import com.critiqo.critiqo_backend.request.token.RefreshTokenRequest;
import com.critiqo.critiqo_backend.response.api.ApiResponse;
import com.critiqo.critiqo_backend.response.auth.AuthResponse;
import com.critiqo.critiqo_backend.response.auth.RefreshTokenResponse;
import com.critiqo.critiqo_backend.service.auth.AuthService;
import com.critiqo.critiqo_backend.service.token.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping(value = "/authenticate")
    private ResponseEntity<ApiResponse<AuthResponse>> authentication(@RequestBody AuthenticationRequest authenticationRequest){
        AuthResponse authResponse = authService.authenticate(authenticationRequest);
        ApiResponse<AuthResponse> apiResponse = ApiResponse.<AuthResponse>builder()
                .data(authResponse)
                .message("Auth")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/refresh-token")
    private ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        RefreshTokenResponse refreshTokenResponse = tokenService.refreshToken(refreshTokenRequest);
        ApiResponse<RefreshTokenResponse> apiResponse = ApiResponse.<RefreshTokenResponse>builder()
                .data(refreshTokenResponse)
                .message("Refresh Token")
                .build();
        return ResponseEntity.ok(apiResponse);
    }



}
