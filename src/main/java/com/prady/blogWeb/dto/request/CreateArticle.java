package com.prady.blogWeb.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreateArticle {

    @NotBlank
    private String title;
    @NotBlank
    private String summary;
    @NotBlank
    private String content;
    @NotBlank
    private String articleType;
    @NotBlank
    private String difficulty;
    @NotBlank
    private List<Long> tagIds;

//-------------------------------------Getter And Setter


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

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }
}
