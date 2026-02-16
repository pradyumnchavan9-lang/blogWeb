package com.prady.blogWeb.repository;


import com.prady.blogWeb.dto.response.ArticleResponse;
import com.prady.blogWeb.entity.Article;
import com.prady.blogWeb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    Page<Article> findAll(Pageable pageable);
    Page<Article> findAllByUser(User user, Pageable pageable);
}
