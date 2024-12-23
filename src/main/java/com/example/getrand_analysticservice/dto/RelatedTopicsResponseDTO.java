package com.example.getrand_analysticservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatedTopicsResponseDTO implements Serializable {
    private Long id;
    private String title;
    private String type;
    private String value;
    private int extractedValue;
    private Date createDate;
    private Date updateDate;
}
