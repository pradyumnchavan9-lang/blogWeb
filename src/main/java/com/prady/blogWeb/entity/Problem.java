package com.prady.blogWeb.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Problem {

    public Problem(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String platform;
    private Long platformProblemId;
    private String title;
    private String difficulty;

    @OneToMany(mappedBy = "problem")
    private List<Article> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getPlatformProblemId() {
        return platformProblemId;
    }

    public void setPlatformProblemId(Long platformProblemId) {
        this.platformProblemId = platformProblemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
