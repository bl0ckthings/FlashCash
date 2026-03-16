package com.Flashcash.Flashcash.service.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailForm {
    @NotBlank(message = "email is mandatory")
    private String email;
}
