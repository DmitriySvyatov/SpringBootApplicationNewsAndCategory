package com.example.springapplication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class NewsDto {
    private Long id;
    private String title;
    private String text;
    private Instant date;
    private String category;


}
