package com.sinnau.domain.board.service;

import com.sinnau.domain.board.model.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllComments();
    List<Comment> getCommentsByPostId(Long postId);
    List<Comment> getCommentsByAuthor(String author);
    Optional<Comment> getCommentById(Long id);
    void deleteComment(Long id);
    void deleteCommentsByPostId(Long postId);
}