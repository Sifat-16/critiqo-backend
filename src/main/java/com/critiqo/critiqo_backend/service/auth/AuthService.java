package com.critiqo.critiqo_backend.service.auth;

import com.critiqo.critiqo_backend.request.auth.AuthenticationRequest;
import com.critiqo.critiqo_backend.response.auth.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthenticationRequest authenticationRequest);
}
