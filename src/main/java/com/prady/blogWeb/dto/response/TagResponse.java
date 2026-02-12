package com.prady.blogWeb.dto.response;

public class TagResponse {

    private Long id;
    private String name;


    //---------------------Getter And Setter
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
}
