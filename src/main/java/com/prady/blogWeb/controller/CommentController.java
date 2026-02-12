package com.prady.blogWeb.controller;


import com.prady.blogWeb.dto.request.CreateComment;
import com.prady.blogWeb.dto.response.CommentResponse;
import com.prady.blogWeb.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article/{articleId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    //Get All Comments for an article (Pagination)
    @GetMapping("")
    public ResponseEntity<Page<CommentResponse>> getAllComments(@PageableDefault(page = 0,size = 10) Pageable pageable,
                                                                @PathVariable Long articleId){
        return ResponseEntity.ok().body(commentService.getAllComments(pageable,articleId));
    }

    //Add Comment to an Article
    @PostMapping("")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long articleId, @RequestBody CreateComment createComment){

        return ResponseEntity.ok().body(commentService.addComment(articleId,createComment));
    }

    //Delete Comment from an article
    @DeleteMapping("/{commentId}")
    ResponseEntity<?> deleteComment(@PathVariable Long articleId,@PathVariable Long commentId){
        commentService.deleteComment(articleId,commentId);
        return ResponseEntity.noContent().build();
    }
}
