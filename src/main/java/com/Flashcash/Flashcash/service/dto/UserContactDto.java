package com.Flashcash.Flashcash.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserContactDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
}
