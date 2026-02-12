package com.prady.blogWeb.controller;


import com.prady.blogWeb.dto.request.CreateProblem;
import com.prady.blogWeb.dto.response.ProblemResponse;
import com.prady.blogWeb.service.ProblemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article/{articleId}/problem")
public class ProblemController {


    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping("")
    public ResponseEntity<ProblemResponse> createProblem(@PathVariable Long articleId,
                                                         @RequestBody CreateProblem createProblem){
        return ResponseEntity.ok().body(problemService.createProblem(articleId, createProblem));
    }
}
