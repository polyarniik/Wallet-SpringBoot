package ru.kpfu.itis.safiullin.walletspringboot.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = HasNumberAndLetterValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasNumberAndLetter {
    String message() default "Пароль должен содержать буквы и числа";

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        HasNumberAndLetter[] value();
    }

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}