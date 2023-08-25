package com.example.githubrepositorydata.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record Repository(String name, String sha){
}
