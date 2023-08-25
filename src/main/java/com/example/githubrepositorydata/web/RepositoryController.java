package com.example.githubrepositorydata.web;

import com.example.githubrepositorydata.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface RepositoryController {

    @GetMapping("/info/{githubOwner}")
    ResponseObject<?> tutorRegisterAccount(@PathVariable("githubOwner") String githubOwner);
}
