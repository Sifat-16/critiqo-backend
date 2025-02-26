package com.critiqo.critiqo_backend.repository.profile;

import com.critiqo.critiqo_backend.model.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Profile findByEmail(String email);
}
