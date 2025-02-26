package com.critiqo.critiqo_backend.service.profile;

import com.critiqo.critiqo_backend.model.profile.Profile;
import com.critiqo.critiqo_backend.request.profile.UpdateProfileRequest;

import java.util.UUID;

public interface ProfileService {
    Profile findProfileByEmail(String email);
    Profile findProfileById(UUID uuid);
    Profile updateProfile(UpdateProfileRequest updateProfileRequest, UUID id);

}
