package com.prady.blogWeb.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateComment {

    @NotBlank
    private String content;


    //------------------Getter and Setter


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
