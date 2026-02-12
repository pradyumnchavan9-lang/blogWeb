package com.prady.blogWeb.controller;


import com.prady.blogWeb.dto.request.CreateTag;
import com.prady.blogWeb.dto.response.TagResponse;
import com.prady.blogWeb.repository.TagRepository;
import com.prady.blogWeb.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    //Create a Tag
    @PostMapping("")
    public ResponseEntity<TagResponse> buildTag(@RequestBody CreateTag createTag){
        return ResponseEntity.ok().body(tagService.buildTag(createTag));
    }

    //Fetch All Tags (Pagination)
    @GetMapping("")
    public ResponseEntity<Page<TagResponse>> getAllTags(@PageableDefault(page = 0,size=10) Pageable pageable){
        return ResponseEntity.ok().body(tagService.getAllTags(pageable));
    }




}
