package com.prady.blogWeb.service;


import com.prady.blogWeb.dto.request.CreateTag;
import com.prady.blogWeb.dto.response.TagResponse;
import com.prady.blogWeb.entity.Article;
import com.prady.blogWeb.entity.Tag;
import com.prady.blogWeb.entity.User;
import com.prady.blogWeb.exception.ResourceAlreadyExistsException;
import com.prady.blogWeb.exception.ResourceNotFoundException;
import com.prady.blogWeb.exception.UnauthorizedActionException;
import com.prady.blogWeb.mapper.TagMapper;
import com.prady.blogWeb.repository.ArticleRepository;
import com.prady.blogWeb.repository.TagRepository;
import com.prady.blogWeb.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagMapper tagMapper;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public TagService(TagMapper tagMapper, TagRepository tagRepository,
                      UserRepository userRepository, ArticleRepository articleRepository) {
        this.tagMapper = tagMapper;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }


    //Create Tag
    public TagResponse buildTag(CreateTag createTag){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not Found: " + username
                ));

        if(!user.getRole().equals("ADMIN")){
            throw new UnauthorizedActionException(
                    "Only Admins can Create Tags"
            );
        }

        tagRepository.findByNameAndCategory(
                createTag.getName(),
                createTag.getCategory()
        ).ifPresent(tag -> {
            throw new ResourceAlreadyExistsException(
                    "Tag already exists " + createTag.getName()
            );
        });

        Tag tag = tagMapper.createTagToTag(createTag);
        tagRepository.save(tag);

        return tagMapper.tagToTagResponse(tag);
    }

    //Get all tags
    public Page<TagResponse> getAllTags(Pageable pageable){
        return tagRepository.findAll(pageable).map(tag -> tagMapper.tagToTagResponse(tag));
    }


}
