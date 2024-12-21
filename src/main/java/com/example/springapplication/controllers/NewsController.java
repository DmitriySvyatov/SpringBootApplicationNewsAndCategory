package com.example.springapplication.controllers;

import com.example.springapplication.dto.NewsDto;
import com.example.springapplication.service.NewsFeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsFeedService newsService;

    public NewsController(NewsFeedService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public NewsDto getNewsById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @GetMapping
    public Collection<NewsDto> getAllNews() {
        return newsService.getAll();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<NewsDto>> getNewsByCategoryId(@PathVariable Long id) {
        List<NewsDto> newsDtoList = newsService.getNewsByCategoryId(id);
        return ResponseEntity.ok(newsDtoList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDto createNews(@RequestBody NewsDto newsDto) {
        return newsService.create(newsDto);
    }

    @PutMapping
    public NewsDto updateNews(@RequestBody NewsDto newsDto) {
        return newsService.update(newsDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable Long id) {
        newsService.delete(id);
    }
}
