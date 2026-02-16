package com.prady.blogWeb.mapper;

import com.prady.blogWeb.dto.request.CreateArticle;
import com.prady.blogWeb.dto.request.UpdateArticle;
import com.prady.blogWeb.dto.response.ArticleIdResponse;
import com.prady.blogWeb.dto.response.ArticleResponse;
import com.prady.blogWeb.dto.response.CommentResponse;
import com.prady.blogWeb.dto.response.TagResponse;
import com.prady.blogWeb.entity.Article;
import com.prady.blogWeb.entity.Comment;
import com.prady.blogWeb.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;


@Component
public class ArticleMapper {

    private final UserMapper userMapper;
    private final TagMapper tagMapper;
    private final CommentMapper commentMapper;
    private final ProblemMapper problemMapper;

    public ArticleMapper(UserMapper userMapper, CommentMapper commentMapper, TagMapper tagMapper, ProblemMapper problemMapper) {
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
        this.tagMapper = tagMapper;
        this.problemMapper = problemMapper;

    }

    public  ArticleIdResponse articleToArticleIdResponse(Article article){

        ArticleIdResponse articleIdResponse = new ArticleIdResponse();
        articleIdResponse.setId(article.getId());
        return articleIdResponse;
    }

    public  ArticleResponse articleToArticleResponse(Article article){

        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setId(article.getId());
        articleResponse.setTitle(article.getTitle());
        articleResponse.setArticleType(article.getArticleType());
        articleResponse.setContent(article.getContent());
        articleResponse.setDifficulty(article.getDifficulty());
        articleResponse.setCreatedAt(article.getCreatedAt());
        articleResponse.setUpdatedAt(article.getUpdatedAt());
        articleResponse.setSummary(article.getSummary());

        //For getting set of tagResponses
        Set<TagResponse> tagResponses = new HashSet<>();
        Set<Tag> tags = article.getTags();
        if(tags != null && !tags.isEmpty()){
            for(Tag tag : tags){
                tagResponses.add(tagMapper.tagToTagResponse(tag));
            }
        }
        articleResponse.setTags(tagResponses);

        //For getting author response
        if (article.getUser() != null) {
            articleResponse.setAuthor(
                    userMapper.userToAuthorResponse(article.getUser())
            );
        }

        //For getting problem
        if (article.getProblem() != null) {
            articleResponse.setProblem(
                    problemMapper.problemToProblemResponse(article.getProblem())
            );
        }

        //For getting CommentResponse from Comments
        List<CommentResponse> commentResponses = new ArrayList<>();
        List<Comment> comments = article.getComments();
        if(comments != null && !comments.isEmpty()){
            for(Comment comment : comments){
                commentResponses.add(commentMapper.commentToCommentResponse(comment));

            }
        }
        articleResponse.setComments(commentResponses);


        return articleResponse;

    }

    public Article createArticleToArticle(CreateArticle createArticle){
        Article article = new Article();
        article.setTitle(createArticle.getTitle());
        article.setArticleType(createArticle.getArticleType());
        article.setContent(createArticle.getContent());
        article.setDifficulty(createArticle.getDifficulty());
        article.setSummary(createArticle.getSummary());
        article.setComments(new ArrayList<>());
        return article;
    }

    public Article updateArticleToArticle(UpdateArticle updateArticle,Article article){

        if(updateArticle.getDifficulty() != null){
            article.setDifficulty(updateArticle.getDifficulty());
        }
        if (updateArticle.getArticleType() != null){
            article.setArticleType(updateArticle.getArticleType());
        }
        if(updateArticle.getSummary() != null){
            article.setSummary(updateArticle.getSummary());
        }
        if(updateArticle.getContent() != null){
            article.setContent(updateArticle.getContent());
        }

        return article;
    }

}
