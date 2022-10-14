package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.web.client.RestTemplate;


import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
public class RedditQueryService {

    private final RestTemplate restTemplate;

    public RedditQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "https://www.reddit.com/r/{subreddit}.json";

    public String getJSON(String subreddit) throws HttpClientErrorException {
        log.info("subreddit={}", subreddit);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("User-Agent","spring-boot:cs156-team01:f22")

        Map<String, String> uriVariables = Map.of("subreddit", subreddit);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class,
                uriVariables);
        return re.getBody();
    }

   

}