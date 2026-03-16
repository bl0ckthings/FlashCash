package com.Flashcash.Flashcash.service.form;

import com.Flashcash.Flashcash.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferForm {
    private String recipientEmail;
    private BigDecimal amount;
}
