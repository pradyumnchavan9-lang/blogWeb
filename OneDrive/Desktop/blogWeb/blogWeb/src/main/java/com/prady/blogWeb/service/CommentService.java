package com.prady.blogWeb.service;


import com.prady.blogWeb.dto.request.CreateComment;
import com.prady.blogWeb.dto.response.CommentResponse;
import com.prady.blogWeb.entity.Article;
import com.prady.blogWeb.entity.Comment;
import com.prady.blogWeb.entity.User;
import com.prady.blogWeb.exception.ResourceNotFoundException;
import com.prady.blogWeb.exception.UnauthorizedActionException;
import com.prady.blogWeb.mapper.CommentMapper;
import com.prady.blogWeb.repository.ArticleRepository;
import com.prady.blogWeb.repository.CommentRepository;
import com.prady.blogWeb.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private final CommentMapper commentMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentService(CommentMapper commentMapper,ArticleRepository articleRepository,
                          UserRepository userRepository,CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


    //Get all Comments
    public Page<CommentResponse> getAllComments(Pageable pageable,Long articleId){

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found: " + articleId));


        return commentRepository.findAllByArticleId(pageable, articleId).map(comment ->
                commentMapper.commentToCommentResponse(comment));
    }


    //Add Comment
    public CommentResponse addComment(Long articleId, CreateComment createComment){
        Comment comment = commentMapper.createCommentToComment(createComment);

        //Get the article using the article id
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Article not found with id" + articleId
                ));
        //Set the article to commment
        comment.setArticle(article);;

        //Get the current user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not Found: " + username
                ));


        //Set the user as the comments author
        comment.setUser(user);

        //Update Changes to the DataBase
        commentRepository.save(comment);

        return commentMapper.commentToCommentResponse(comment);
    }


    //Delete Comment
    public void deleteComment(Long articleId, Long commentId){

        //Before Deletion check if the logged-in user matches with the author of comment
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User Not Found: " + username
                ));
        //Get the comment from the commentId
        Comment comment  = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment Not Found: " + commentId
                ));

        if(!comment.getUser().equals(user) && !user.getRole().equals("ADMIN")){
         throw new UnauthorizedActionException("You are not Allowed to delete this comment");
        }

        if(!comment.getArticle().getId().equals(articleId)){
            throw new UnauthorizedActionException("Comment does not belong to this article");
        }

        commentRepository.delete(comment);
    }
}
