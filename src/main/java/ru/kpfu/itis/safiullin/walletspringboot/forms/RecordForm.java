package ru.kpfu.itis.safiullin.walletspringboot.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordForm {
    @NotBlank
    private Long bankId;

    @NotBlank
    private Long categoryId;

    @Min(0)
    @NotBlank
    private Float sum;

    @NotBlank
    private Boolean isIncome;

    @Length(max = 50)
    private String description;

    private Date date;
}
