package com.example.getrand_analysticservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultPastOYResponseDTO implements Serializable {
    private String date;
    private String value;
}
