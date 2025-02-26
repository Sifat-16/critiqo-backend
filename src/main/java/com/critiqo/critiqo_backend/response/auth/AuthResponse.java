package com.critiqo.critiqo_backend.response.auth;

import com.critiqo.critiqo_backend.model.token.AuthToken;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

enum AuthResponseType{
    LOGIN,
    SIGNUP
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    @NotEmpty
    private AuthResponseType authResponseType;
    @NotEmpty
    private String email;
    private String userName;

    @NotEmpty
    private AuthToken authToken;
}
