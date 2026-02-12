package com.prady.blogWeb.mapper;

import com.prady.blogWeb.dto.request.CreateTag;
import com.prady.blogWeb.dto.response.TagResponse;
import com.prady.blogWeb.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class TagMapper {

    public  TagResponse tagToTagResponse(Tag tag){

        TagResponse tagResponse = new TagResponse();
        tagResponse.setId(tag.getId());
        tagResponse.setName(tag.getName());
        return tagResponse;
    }

    public Tag createTagToTag(CreateTag createTag){
        Tag tag = new Tag();
        tag.setName(createTag.getName());
        tag.setCategory(createTag.getCategory());
        tag.setArticles(new HashSet<>());

        return tag;
    }
}
