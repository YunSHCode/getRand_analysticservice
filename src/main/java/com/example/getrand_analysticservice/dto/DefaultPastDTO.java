package com.example.getrand_analysticservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultPastDTO {
    private Long id;
    private String date;
    private String value;
    private Date createDate;
    private Date updateDate;
}
