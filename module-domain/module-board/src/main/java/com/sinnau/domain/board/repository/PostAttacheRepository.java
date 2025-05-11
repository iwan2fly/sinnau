package com.sinnau.domain.board.repository;

import com.sinnau.domain.board.model.entity.PostAttache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostAttacheRepository extends JpaRepository<PostAttache, Long> {
    List<PostAttache> findByPostPostId(Long postId);
    void deleteByPostPostId(Long postId);
}