package org.example.s31087tpo10.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CorrectPasswordValidator implements ConstraintValidator<CorrectPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isBlank()) {
            return true;
        }
        boolean valid = true;
        int lower = 0, upper = 0, digit = 0, special = 0;
        for (char ch : password.toCharArray()) {
            if (Character.isLowerCase(ch)) lower++;
            else if (Character.isUpperCase(ch)) upper++;
            else if (Character.isDigit(ch)) digit++;
            else special++;
        }

        context.disableDefaultConstraintViolation();

        if (password.length() < 10) {
            context.buildConstraintViolationWithTemplate("{password.tooShort}")
                    .addConstraintViolation();
            valid = false;
        }
        if (lower < 1) {
            context.buildConstraintViolationWithTemplate("{password.noLower}")
                    .addConstraintViolation();
            valid = false;
        }
        if (upper < 2) {
            context.buildConstraintViolationWithTemplate("{password.noUpper}")
                    .addConstraintViolation();
            valid = false;
        }
        if (digit < 3) {
            context.buildConstraintViolationWithTemplate("{password.noDigits}")
                    .addConstraintViolation();
            valid = false;
        }
        if (special < 4) {
            context.buildConstraintViolationWithTemplate("{password.noSpecial}")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
