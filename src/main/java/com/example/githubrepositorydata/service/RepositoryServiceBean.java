package com.example.githubrepositorydata.service;

import com.example.githubrepositorydata.dto.Repository;
import com.example.githubrepositorydata.dto.OwnerInfo;
import com.example.githubrepositorydata.dto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RepositoryServiceBean implements RepositoryService {

    @Override
    public ResponseObject<?> getUserRepositories(String githubOwner) {
        var restTemplate = new RestTemplate();
        String GIT_HUB_API = "https://api.github.com/users/";
        ArrayList<OwnerInfo> ownerInfo;
        try {
            ResponseEntity<OwnerInfo[]> responseEntity = restTemplate.getForEntity(GIT_HUB_API + githubOwner + "/repos", OwnerInfo[].class);
            ownerInfo = new ArrayList<>(Arrays.asList(Objects.requireNonNull(responseEntity.getBody())));
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "USER_NOT_FOUND");
        }

        List<Repository> filteredRepositoryNames = ownerInfo.stream()
                .filter(repo -> repo.forks() >= 1)
                .map(repo -> {
                    String repoName = repo.name();
                    String repoUrl = repo.url();

                    ResponseEntity<Map[]> commitsResponse = restTemplate.getForEntity(repoUrl + "/commits", Map[].class);
                    Map[] commits = commitsResponse.getBody();

                    if (commits != null && commits.length > 0) {
                        String latestCommitSha = (String) commits[0].get("sha");
                        return new Repository(repoName, latestCommitSha);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        HttpStatus httpStatus = HttpStatus.OK;

        return new ResponseObject<>(httpStatus, filteredRepositoryNames);


    }
}
