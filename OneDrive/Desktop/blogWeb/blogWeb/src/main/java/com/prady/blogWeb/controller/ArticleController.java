package com.prady.blogWeb.controller;


import com.prady.blogWeb.dto.request.CreateArticle;
import com.prady.blogWeb.dto.request.QuestionRequest;
import com.prady.blogWeb.dto.request.UpdateArticle;
import com.prady.blogWeb.dto.response.ArticleResponse;
import com.prady.blogWeb.dto.response.ChatResponse;
import com.prady.blogWeb.dto.response.PageResponseDto;
import com.prady.blogWeb.service.ArticleService;
import com.prady.blogWeb.service.ChatService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final ChatService chatService;

    public ArticleController(ArticleService articleService, ChatService chatService) {
        this.articleService = articleService;
        this.chatService = chatService;
    }


    //Get Article by User
    @GetMapping("/{userId}")
    public ResponseEntity<Page<ArticleResponse>> getArticle(@PathVariable Long userId,
                                                            @PageableDefault(page = 0,size = 10) Pageable pageable ){

        return ResponseEntity.ok().body(articleService.getArticle(userId,pageable));
    }


    //Create Article
    @PostMapping("")
    public ResponseEntity<ArticleResponse> createArticle(@RequestBody CreateArticle createArticle){

        return ResponseEntity.ok().body(articleService.create(createArticle));
    }

    //Update Article
    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id,@RequestBody UpdateArticle updateArticle){

        return ResponseEntity.ok().body(articleService.updateArticleById(id,updateArticle));
    }

    //Delete Article
    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteArticle(@PathVariable Long id){

        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }


    //Get Articles(Pagination :- Only a set of articles)
    @GetMapping("")
    public ResponseEntity<PageResponseDto<ArticleResponse>> getAllArticles(@PageableDefault(page = 0,size = 10) Pageable pageable){

            return ResponseEntity.ok().body(articleService.getAllArticles(pageable));
    }

    //Add tag to Article
    @PostMapping("/{id}/{tagId}")
    public ResponseEntity<?> addTagToArtice(@PathVariable Long id, @PathVariable Long tagId){
        articleService.addTagToArticle(id,tagId);
        return ResponseEntity.ok().build();
    }


    //Get Question to be answered by The Bot
    @PostMapping("/{articleId}/chat")
    public ResponseEntity<ChatResponse> getAnswer(@RequestBody QuestionRequest questionRequest, @PathVariable Long articleId){

        return new ResponseEntity<>(chatService.getAnswer(questionRequest,articleId), HttpStatus.OK);
    }

}
