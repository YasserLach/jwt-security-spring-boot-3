package com.authentication.autentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenericDto {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
