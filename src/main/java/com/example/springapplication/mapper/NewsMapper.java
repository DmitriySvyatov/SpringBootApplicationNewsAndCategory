package com.example.springapplication.mapper;

import com.example.springapplication.dto.NewsDto;
import com.example.springapplication.entity.News;

import java.time.Instant;

public class NewsMapper {
    public static NewsDto mapToDto(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .text(news.getText())
                .date(news.getDate())
                .category(news.getCategory().getTitle())
                .build();
    }
    public static News mapToEntity(NewsDto newsDto) {
        return News.builder()
                .id(newsDto.getId())
                .title(newsDto.getTitle())
                .text(newsDto.getText())
                .date(newsDto.getDate() != null ? newsDto.getDate() : Instant.now())
                .build();
    }
}
