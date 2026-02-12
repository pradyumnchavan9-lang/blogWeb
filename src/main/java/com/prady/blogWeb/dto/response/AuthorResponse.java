package com.prady.blogWeb.dto.response;

public class AuthorResponse {

    private Long id;
    private String username;


    //-------------------------Getter And Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
