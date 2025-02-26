package com.critiqo.critiqo_backend.service.token;

import com.critiqo.critiqo_backend.exception.expired.TokenExpiredException;
import com.critiqo.critiqo_backend.exception.validation.TokenValidationException;
import com.critiqo.critiqo_backend.model.token.AuthToken;
import com.critiqo.critiqo_backend.request.token.RefreshTokenRequest;
import com.critiqo.critiqo_backend.response.auth.RefreshTokenResponse;
import com.critiqo.critiqo_backend.response.auth.TokenValidationType;
import com.critiqo.critiqo_backend.service.jwt.JwtService;
import com.critiqo.critiqo_backend.service.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

    private final JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthToken generateAuthToken(String email) {
        String accessToken = jwtService.generateAccessToken(email);
        String refreshToken = jwtService.generateRefreshToken(email);
        return AuthToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        RefreshTokenResponse refreshTokenResponse;

        String username = jwtService.extractUsername(refreshTokenRequest.getRefreshToken());
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        boolean isRefreshTokenValid = jwtService.isValidToken(refreshTokenRequest.getRefreshToken(), userDetails);

        if(isRefreshTokenValid){
            AuthToken authToken = generateAuthToken(username);
            refreshTokenResponse = RefreshTokenResponse.builder()
                    .tokenValidationType(TokenValidationType.VALID.name())
                    .authToken(authToken)
                    .build();

        }else{
            throw new TokenValidationException("Token is not valid");
        }
        return refreshTokenResponse;
    }
}
