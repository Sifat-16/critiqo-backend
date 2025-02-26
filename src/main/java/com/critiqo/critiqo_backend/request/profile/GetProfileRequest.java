package com.critiqo.critiqo_backend.request.profile;

import com.critiqo.critiqo_backend.annotation.validation.ValidIdentity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidIdentity
public class GetProfileRequest {
    @NotEmpty
    private String type;

    @NotEmpty
    private String identity;

}
