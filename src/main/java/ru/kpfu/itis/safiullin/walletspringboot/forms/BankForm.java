package ru.kpfu.itis.safiullin.walletspringboot.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankForm {
    @NotNull
    private String name;
    @NotNull
    private String color;
    @NotNull
    private Float amount;

}
