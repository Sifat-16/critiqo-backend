package com.critiqo.critiqo_backend.request.profile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileRequest {
    @Size(max = 100, message = "Name must be between 100 characters")
    private String name;

    private String address;

    @Size(min = 10, max = 15, message = "Contact number must be between 10 and 15 characters")
    private String contactNumber;

    private String profileUrl;

    private String bio;

}
