package ru.kpfu.itis.safiullin.walletspringboot.forms;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.kpfu.itis.safiullin.walletspringboot.validation.HasNumberAndLetter;
import ru.kpfu.itis.safiullin.walletspringboot.validation.PasswordEquals;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordEquals(password1 = "password1", password2 = "password2", message = "Пароли не совпадают")
public class SignUpForm {
    @NotBlank
    @Length(min = 3, message = "Минимальная длина имени 8 символов")
    @Length(max = 64, message = "Максимальная длина имени 8 символов")
    private String name;
    @Email(message = "Неправильный Email")
    private String email;
    @Length(min= 8, message = "Минимальная длина пароля 8 символов")
    @Length(max= 256, message = "Максимальная длина пароля 256 символов")
    @HasNumberAndLetter
    private String password1;
    private String password2;
}
