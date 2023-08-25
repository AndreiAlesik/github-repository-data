package com.example.githubrepositorydata.service;

import com.example.githubrepositorydata.dto.Link;
import com.example.githubrepositorydata.dto.OwnerInfo;
import com.example.githubrepositorydata.dto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RepositoryServiceBean implements RepositoryService {

    @Override
    public ResponseObject<?> getUserRepositories(String githubOwner) {
        var restTemplate = new RestTemplate();
        String GIT_HUB_API = "https://api.github.com/users/";
        ResponseEntity<OwnerInfo[]> responseEntity = restTemplate.getForEntity(GIT_HUB_API +githubOwner+"/repos", OwnerInfo[].class);
        ArrayList<OwnerInfo> ownerInfo;
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ownerInfo = new ArrayList<>(Arrays.asList(Objects.requireNonNull(responseEntity.getBody())));
        }
        else {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "USER_NOT_FOUND");
        }
        List<Link> filteredRepositoryNames = ownerInfo.stream()
                .filter(repo -> repo.forks() >= 1)
                .map(repo -> new Link(repo.name()))
                .collect(Collectors.toList());

        HttpStatus httpStatus = HttpStatus.OK;

        return new ResponseObject<>(httpStatus, filteredRepositoryNames);


    }
}
