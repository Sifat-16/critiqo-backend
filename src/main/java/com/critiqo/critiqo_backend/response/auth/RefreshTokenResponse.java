package com.critiqo.critiqo_backend.response.auth;

import com.critiqo.critiqo_backend.model.token.AuthToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenResponse {
    private String tokenValidationType;
    private AuthToken authToken;
}
