package com.restfulservices.rest.repository;

import com.restfulservices.rest.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
