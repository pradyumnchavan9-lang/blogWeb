package com.prady.blogWeb.repository;

import com.prady.blogWeb.entity.Article;
import com.prady.blogWeb.entity.Tag;
import com.prady.blogWeb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

}
