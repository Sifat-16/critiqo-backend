package com.critiqo.critiqo_backend.controller.profile;

import com.critiqo.critiqo_backend.model.profile.Profile;
import com.critiqo.critiqo_backend.request.profile.GetProfileRequest;
import com.critiqo.critiqo_backend.response.api.ApiResponse;
import com.critiqo.critiqo_backend.service.profile.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "${api.prefix}/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ApiResponse<Profile>> fetchProfile(@Valid @ModelAttribute GetProfileRequest getProfileRequest){

        Profile profile;

        if(Objects.equals(getProfileRequest.getType(), "id")){
            UUID uuid = UUID.fromString(getProfileRequest.getIdentity());
            profile = profileService.findProfileById(uuid);
        }else{
            profile = profileService.findProfileByEmail(getProfileRequest.getIdentity());
        }

        ApiResponse<Profile> apiResponse = ApiResponse.<Profile>builder()
                .message("profile")
                .data(profile)
                .build();

        return ResponseEntity.ok(apiResponse);

    }


}
