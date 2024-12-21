package com.example.springapplication.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id){
        super("Категория с id " + id + " не найдена.");
    }
}
