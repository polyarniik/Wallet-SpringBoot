package ru.kpfu.itis.safiullin.walletspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.safiullin.walletspringboot.models.Bank;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankDto {
    private Long id;
    private String name;
    private String color;
    private Float amount;

    public static BankDto fromBank(Bank bank) {
        return BankDto.builder()
                .id(bank.getId())
                .name(bank.getName())
                .color(bank.getColor())
                .amount(bank.getAmount())
                .build();
    }

    public static List<BankDto> from(List<Bank> banks) {
        return banks.stream().map(BankDto::fromBank).collect(Collectors.toList());
    }
}
