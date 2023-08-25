package com.example.githubrepositorydata.dto;

import org.springframework.http.HttpStatus;

public record ResponseObject<T>(
        HttpStatus status,
        T message
) {
}
