package com.sinnau.domain.board.repository;

import com.sinnau.domain.board.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostPostId(Long postId);
    List<Comment> findByAuthor(String author);
    void deleteByPostPostId(Long postId);
}