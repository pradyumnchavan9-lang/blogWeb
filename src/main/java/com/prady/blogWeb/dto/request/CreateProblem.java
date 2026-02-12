package com.prady.blogWeb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProblem {

    @NotBlank
    private String platform;
    @NotNull
    private Long platformProblemId;
    @NotBlank
    private String title;
    @NotBlank
    private String difficulty;


    //--------------------------------Getter And Setter


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
}
