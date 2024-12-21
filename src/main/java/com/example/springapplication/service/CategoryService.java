package com.example.springapplication.service;

import com.example.springapplication.dto.CategoryDto;
import com.example.springapplication.entity.Category;
import com.example.springapplication.exception.CategoryNotFoundException;
import com.example.springapplication.mapper.CategoryMapper;
import com.example.springapplication.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService implements CRUDService<CategoryDto> {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto getById(Long id) {
        log.info("Get by Id: " + id);
        return CategoryMapper.mapToDto(categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id)));
    }

    @Override
    public Collection<CategoryDto> getAll() {
        log.info("Get All");
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::mapToDto)
                .toList();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        log.info("Create");
        Category category = CategoryMapper.mapToEntity(categoryDto);
        return CategoryMapper.mapToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        log.info("Update " + categoryDto.getId());
        Category existingCategory = categoryRepository
                .findById(categoryDto.getId())
                .orElseThrow(() -> new CategoryNotFoundException(categoryDto.getId()));
        existingCategory.setTitle(categoryDto.getTitle());
        return CategoryMapper.mapToDto(categoryRepository.save(existingCategory));
    }

    @Override
    public void delete(Long id) {
        log.info("Delete " + id);
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.deleteById(category.getId());
    }

}
