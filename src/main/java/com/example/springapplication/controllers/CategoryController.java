package com.example.springapplication.controllers;

import com.example.springapplication.dto.CategoryDto;
import com.example.springapplication.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping
    public Collection<CategoryDto> getAll(){
        return categoryService.getAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {

        return categoryService.create(categoryDto);
    }
    @PutMapping
    public CategoryDto update( @RequestBody CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
