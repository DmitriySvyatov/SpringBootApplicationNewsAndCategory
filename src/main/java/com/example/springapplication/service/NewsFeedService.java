package com.example.springapplication.service;

import com.example.springapplication.dto.NewsDto;
import com.example.springapplication.entity.Category;
import com.example.springapplication.entity.News;
import com.example.springapplication.exception.CategoryNotFoundException;
import com.example.springapplication.exception.NewsNotFoundException;
import com.example.springapplication.mapper.NewsMapper;
import com.example.springapplication.repositories.CategoryRepository;
import com.example.springapplication.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsFeedService implements CRUDService<NewsDto> {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public NewsDto getById(Long id) {
        log.info("Get by ID: " + id);
        News news = newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException(id));
        return NewsMapper.mapToDto(news);
    }

    @Override
    public Collection<NewsDto> getAll() {
        log.info("Get All");
        return newsRepository.findAll()
                .stream()
                .map(NewsMapper::mapToDto)
                .toList();
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        log.info("Create News: {}", newsDto);
        String categoryTitle = newsDto.getCategory();
        Category category = categoryRepository.findByTitle(categoryTitle)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setTitle(categoryTitle);
                    return categoryRepository.save(newCategory);
                });
        News news = NewsMapper.mapToEntity(newsDto);
        news.setCategory(category);
        News savedNews = newsRepository.save(news);
        return NewsMapper.mapToDto(savedNews);
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        log.info("Update " + newsDto);
        News existingNews = newsRepository
                .findById(newsDto.getId())
                .orElseThrow(() -> new NewsNotFoundException(newsDto.getId()));
        News updateNews = NewsMapper.mapToEntity(newsDto);
        String categoryTitle = newsDto.getCategory();
        Category category = categoryRepository.findByTitle(categoryTitle)
                        .orElseGet(() -> {
                            Category newCategory = new Category();
                            newCategory.setTitle(categoryTitle);
                            return categoryRepository.save(newCategory);
                        });
        updateNews.setCategory(category);
        updateNews.setId(existingNews.getId());
        News updatedNews = newsRepository.save(updateNews);
        return NewsMapper.mapToDto(updatedNews);
    }

    @Override
    public void delete(Long id) {
        log.info("Delete " + id);
        News news = newsRepository
                .findById(id)
                .orElseThrow(() -> new NewsNotFoundException(id));
        newsRepository.deleteById(news.getId());
    }
    public List<NewsDto> getNewsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        List<News> newsList = newsRepository.findByCategory(category);
        return newsList.stream()
                .map(NewsMapper::mapToDto)
                .toList();
    }



}
