package com.example.blogsystem.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
