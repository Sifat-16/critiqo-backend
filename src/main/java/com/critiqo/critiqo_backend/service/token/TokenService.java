package com.critiqo.critiqo_backend.service.token;

import com.critiqo.critiqo_backend.model.token.AuthToken;
import com.critiqo.critiqo_backend.request.token.RefreshTokenRequest;
import com.critiqo.critiqo_backend.response.auth.RefreshTokenResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


public interface TokenService {
    AuthToken generateAuthToken(String email);

    RefreshTokenResponse refreshToken(@Valid RefreshTokenRequest refreshTokenRequest);

}
