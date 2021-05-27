package ru.kpfu.itis.safiullin.walletspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.safiullin.walletspringboot.models.Record;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordDto {
    private Long id;
    private BankDto bank;
    private CategoryDto category;
    private AccountDto account;
    private Float sum;
    private String description;
    private Date date;

    public static RecordDto fromRecord(Record record) {
        return RecordDto.builder()
                .id(record.getId())
                .bank(BankDto.fromBank(record.getBank()))
                .category(CategoryDto.fromCategory(record.getCategory()))
                .account(AccountDto.fromAccount(record.getAccount()))
                .sum(record.getSum())
                .description(record.getDescription())
                .date(record.getDate())
                .build();
    }

    public static List<RecordDto> from(List<Record> records) {
        return records.stream().map(RecordDto::fromRecord).collect(Collectors.toList());
    }
}
