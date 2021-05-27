package ru.kpfu.itis.safiullin.walletspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.safiullin.walletspringboot.models.Account;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Long id;
    private String name;
    private String email;
    private List<BankDto> bankList;

    public static AccountDto fromAccount(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .bankList(account.getBanks().stream().map(BankDto::fromBank).collect(Collectors.toList()))
                .build();
    }
}
