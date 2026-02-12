package com.prady.blogWeb.service;


import com.prady.blogWeb.dto.request.QuestionRequest;
import com.prady.blogWeb.dto.response.ChatResponse;
import com.prady.blogWeb.entity.Article;
import com.prady.blogWeb.exception.ResourceNotFoundException;
import com.prady.blogWeb.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    @Value("${api.key}")
    private String apiKey;
    @Value("${api.url}")
    private String url;

    private ArticleRepository articleRepository;
    private RestTemplate restTemplate;

    public ChatService(ArticleRepository articleRepository, RestTemplate restTemplate) {
        this.articleRepository = articleRepository;
        this.restTemplate = restTemplate;
    }


    public ChatResponse getAnswer(QuestionRequest questionRequest,Long articleId){


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String question = "";

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Article Not Found: " + articleId
                ));
        String content = article.getContent();

        question = "Answer question: " + questionRequest.getQuestion() +" For this Content: " + content;

        Map<String,Object> body = new HashMap<>();
        body.put("model","llama-3.3-70b-versatile");

        List<Map<String,String>> messages = new ArrayList<>();
        Map<String,String> message = new HashMap<>();
        message.put("role","user");
        message.put("content",question);

        messages.add(message);
        body.put("messages",messages);

        HttpEntity<Map<String,Object>> httpEntity = new HttpEntity<>(body,headers);
        System.out.println("METHOD: POST");
        System.out.println(httpEntity);

        ResponseEntity<ChatResponse> chatResponse = restTemplate.exchange(url, HttpMethod.POST,httpEntity, ChatResponse.class);


        return chatResponse.getBody();
    }

}
