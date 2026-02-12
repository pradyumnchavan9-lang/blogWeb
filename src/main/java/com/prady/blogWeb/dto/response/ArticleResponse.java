package com.prady.blogWeb.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ArticleResponse {

    private Long id;
    private String title;
    private String summary;
    private String content;
    private String articleType;
    private String difficulty;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuthorResponse author;
    private Set<TagResponse> tags;
    private ProblemResponse problem;
    private List<CommentResponse> comments;


    //-------------------------Getter And Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AuthorResponse getAuthor() {
        return author;
    }

    public void setAuthor(AuthorResponse author) {
        this.author = author;
    }

    public Set<TagResponse> getTags() {
        return tags;
    }

    public void setTags(Set<TagResponse> tags) {
        this.tags = tags;
    }

    public ProblemResponse getProblem() {
        return problem;
    }

    public void setProblem(ProblemResponse problem) {
        this.problem = problem;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }
    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
