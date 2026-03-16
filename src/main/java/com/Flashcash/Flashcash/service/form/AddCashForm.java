package com.Flashcash.Flashcash.service.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCashForm {
    private Integer accountId;
    private BigDecimal amount;

}
