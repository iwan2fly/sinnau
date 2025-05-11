package com.sinnau.domain.board.repository;

import com.sinnau.domain.board.entity.PostKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostKeywordRepository extends JpaRepository<PostKeyword, Long> {
    List<PostKeyword> findByKeyword(String keyword);
    List<PostKeyword> findByPostPostId(Long postId);
    void deleteByPostPostId(Long postId);
}