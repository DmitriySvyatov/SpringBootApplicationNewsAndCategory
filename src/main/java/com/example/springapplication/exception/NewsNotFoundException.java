package com.example.springapplication.exception;

public class NewsNotFoundException extends RuntimeException{
    public NewsNotFoundException(Long id) {
        super("Новость с ID " + id + " не найдена.");
    }
}
