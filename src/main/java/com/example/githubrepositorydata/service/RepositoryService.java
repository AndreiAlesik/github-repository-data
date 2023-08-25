package com.example.githubrepositorydata.service;

import com.example.githubrepositorydata.dto.ResponseObject;

public interface RepositoryService {
    ResponseObject getUserRepositories(String githubOwner);
}
