package com.critiqo.critiqo_backend.request.token;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenRequest {
    @NotEmpty
    private String accessToken;
    @NotEmpty
    private String refreshToken;

}
