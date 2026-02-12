package com.prady.blogWeb.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    public Tag(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;


    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles = new HashSet<>();


    // Helper methods

    public void addArticle(Article article){
        if(article != null) {
            articles.add(article);
            article.addTag(this);
        }
        return;
    }

    public void removeArticle(Article article){
        if(article != null && articles.contains(article)) {
            articles.remove(article);
            article.removeTag(this);
        }
        return;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }


}
