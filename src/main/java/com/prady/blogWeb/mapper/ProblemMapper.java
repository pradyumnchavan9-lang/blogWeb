package com.prady.blogWeb.mapper;

import com.prady.blogWeb.dto.request.CreateProblem;
import com.prady.blogWeb.dto.response.ProblemResponse;
import com.prady.blogWeb.entity.Problem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class ProblemMapper {

    public  ProblemResponse problemToProblemResponse(Problem problem){

        ProblemResponse problemResponse = new ProblemResponse();
        problemResponse.setId(problem.getId());
        problemResponse.setTitle(problem.getTitle());
        problemResponse.setDifficulty(problem.getDifficulty());
        problemResponse.setPlatform(problem.getPlatform());

        return problemResponse;
    }

    public Problem createProblemToProblem(CreateProblem createProblem){
        Problem problem = new Problem();
        problem.setTitle(createProblem.getTitle());
        problem.setDifficulty(createProblem.getDifficulty());
        problem.setPlatform(createProblem.getPlatform());
        problem.setPlatformProblemId(createProblem.getPlatformProblemId());
        problem.setArticles(new ArrayList<>());

        return problem;
    }
}
