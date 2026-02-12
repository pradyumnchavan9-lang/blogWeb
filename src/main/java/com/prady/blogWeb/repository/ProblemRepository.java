package com.prady.blogWeb.repository;


import com.prady.blogWeb.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  ProblemRepository extends JpaRepository<Problem,Long> {

    Optional<Problem> findByPlatformAndPlatformProblemId(String  platform,Long platformProblemId);
}
