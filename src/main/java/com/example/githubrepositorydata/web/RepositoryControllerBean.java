package com.example.githubrepositorydata.web;

import com.example.githubrepositorydata.dto.ResponseObject;
import com.example.githubrepositorydata.service.RepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CrossOrigin
public class RepositoryControllerBean implements RepositoryController {
    private final RepositoryService repositoryService;

    public RepositoryControllerBean(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public ResponseObject<?> getUserInfo(
            String githubOwner
    ) {
        return repositoryService.getUserRepositories(githubOwner);
    }
}
