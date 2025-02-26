package com.critiqo.critiqo_backend.request.user;

import com.critiqo.critiqo_backend.model.user.UserRole;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    @NotEmpty
    private String email;

    @NotEmpty
    private UserRole userRole;
}
