package com.example.githubrepositorydata.web;

import com.example.githubrepositorydata.dto.ResponseObject;
import org.springframework.web.bind.annotation.*;

public interface RepositoryController {

    @GetMapping("/info/{githubOwner}")
    ResponseObject<?> getUserInfo(@PathVariable("githubOwner") String githubOwner);
}
