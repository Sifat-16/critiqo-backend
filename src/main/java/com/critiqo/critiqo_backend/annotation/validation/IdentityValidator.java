package com.critiqo.critiqo_backend.annotation.validation;

import com.critiqo.critiqo_backend.request.profile.GetProfileRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;
import java.util.regex.Pattern;

public class IdentityValidator implements ConstraintValidator<ValidIdentity, GetProfileRequest> {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");



    @Override
    public boolean isValid(GetProfileRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request == null || request.getType() == null || request.getIdentity() == null) {
            return false;
        }

        String type = request.getType();
        String identity = request.getIdentity();

        if ("id".equalsIgnoreCase(type)) {
            return isValidUUID(identity);
        } else if ("email".equalsIgnoreCase(type)) {
            return isValidEmail(identity);
        }

        return false;
    }


    private boolean isValidUUID(String identity) {
        try {
            UUID.fromString(identity);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidEmail(String identity) {
        return EMAIL_PATTERN.matcher(identity).matches();
    }
}
