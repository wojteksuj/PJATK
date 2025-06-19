package org.example.s31087tpo10.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CorrectPasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectPassword {
    String message() default "Password must have at least 1 lowercase, 2 uppercase, 3 digits, 4 special characters, and be at least 10 characters long.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

