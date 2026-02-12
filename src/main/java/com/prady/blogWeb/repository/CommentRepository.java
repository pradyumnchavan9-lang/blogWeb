package com.prady.blogWeb.repository;

import com.prady.blogWeb.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    Page<Comment> findAllByArticleId(Pageable pageable, Long articleId);
    List<Comment> findAllByArticleId(Long articleId);

}
