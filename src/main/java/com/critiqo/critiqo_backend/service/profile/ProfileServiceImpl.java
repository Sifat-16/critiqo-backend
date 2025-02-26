package com.critiqo.critiqo_backend.service.profile;

import com.critiqo.critiqo_backend.exception.resource.ResourceNotFoundException;
import com.critiqo.critiqo_backend.model.profile.Profile;
import com.critiqo.critiqo_backend.repository.profile.ProfileRepository;
import com.critiqo.critiqo_backend.request.profile.UpdateProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final ProfileRepository profileRepository;

    @Override
    public Profile findProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public Profile findProfileById(UUID uuid) {
        return profileRepository.findById(uuid).orElseThrow(()->new ResourceNotFoundException("Profile not found"));
    }

    @Override
    public Profile updateProfile(UpdateProfileRequest updateProfileRequest, UUID id) {

        Profile profile = profileRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Profile not found"));

        if(updateProfileRequest.getName()!=null){
            profile.setName(updateProfileRequest.getName());
        }
        if(updateProfileRequest.getProfileUrl()!=null){
            profile.setProfileUrl(updateProfileRequest.getProfileUrl());
        }
        if(updateProfileRequest.getContactNumber()!=null){
            profile.setContactNumber(updateProfileRequest.getContactNumber());
        }

        if(updateProfileRequest.getAddress()!=null){
            profile.setAddress(updateProfileRequest.getAddress());
        }

        if(updateProfileRequest.getBio()!=null){
            profile.setBio(updateProfileRequest.getBio());
        }

        return profileRepository.save(profile);
    }
}
