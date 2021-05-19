package ru.kpfu.itis.safiullin.walletspringboot.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordEqualsValidator implements ConstraintValidator<PasswordEquals, Object> {

    private String password1;
    private String password2;

    @Override
    public void initialize(PasswordEquals constraintAnnotation) {
        password1 = constraintAnnotation.password1();
        password2 = constraintAnnotation.password2();
    }


    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object password = new BeanWrapperImpl(o).getPropertyValue(password1);
        Object passwordRepeat = new BeanWrapperImpl(o).getPropertyValue(password2);
        return password != null && password.equals(passwordRepeat);
    }
}