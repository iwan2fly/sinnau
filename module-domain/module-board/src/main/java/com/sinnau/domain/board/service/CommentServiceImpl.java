package com.sinnau.domain.board.service;

import com.sinnau.domain.board.model.entity.Comment;
import com.sinnau.domain.board.model.entity.Post;
import com.sinnau.domain.board.exception.CommentNotFoundException;
import com.sinnau.domain.board.exception.PostNotFoundException;
import com.sinnau.domain.board.repository.CommentRepository;
import com.sinnau.domain.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostPostId(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByAuthor(String author) {
        return commentRepository.findByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment with id " + id + " not found"));
        commentRepository.delete(comment);
    }

    @Override
    public void deleteCommentsByPostId(Long postId) {
        commentRepository.deleteByPostPostId(postId);
    }
}