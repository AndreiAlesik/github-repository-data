package com.example.githubrepositorydata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record OwnerInfo(
        String name,
        Integer forks,
        @JsonProperty("updated_at")
        LocalDateTime lastCommit
) {}
