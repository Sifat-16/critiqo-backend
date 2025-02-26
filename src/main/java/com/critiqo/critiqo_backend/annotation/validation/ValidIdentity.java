package com.critiqo.critiqo_backend.annotation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdentityValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIdentity {
    String message() default "Invalid identity format based on type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
