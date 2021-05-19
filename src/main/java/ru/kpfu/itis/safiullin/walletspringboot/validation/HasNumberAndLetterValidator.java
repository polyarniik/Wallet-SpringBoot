package ru.kpfu.itis.safiullin.walletspringboot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HasNumberAndLetterValidator implements ConstraintValidator<HasNumberAndLetter, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.matches(".*[0-9].*") && value.matches(".*[a-zA-Z].*");
    }
}