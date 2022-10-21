package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.RedditQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "Get posts from a subreddit on Reddit.com")
@Slf4j
@RestController
@RequestMapping("/api/reddit")
public class RedditController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    RedditQueryService RedditQueryService;

    @ApiOperation(value = "Get subreddit info")
    @GetMapping("/get")
    public ResponseEntity<String> getSubreddit(
        @ApiParam("Subreddit") @RequestParam String subreddit
    ) throws JsonProcessingException {
        log.info("getSubreddit: subreddit={}", subreddit);
        String result = RedditQueryService.getJSON(subreddit);
        return ResponseEntity.ok().body(result);
    }

}