package com.critiqo.critiqo_backend.model.profile;

import com.critiqo.critiqo_backend.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @Column(length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 15)
    @Size(min = 10, max = 15, message = "Contact number must be between 10 and 15 characters")
    private String contactNumber;

    @Column(columnDefinition = "TEXT")
    private String profileUrl;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
