package org.example.s31087tpo10.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.s31087tpo10.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUrlValidator implements ConstraintValidator<UniqueUrl, String> {

    @Autowired
    private LinkRepository linkRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || !linkRepository.existsByTargetUrl(value);
    }
}

