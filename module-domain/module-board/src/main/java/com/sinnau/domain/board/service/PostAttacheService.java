package com.sinnau.domain.board.service;

import com.sinnau.domain.board.model.entity.PostAttache;

import java.util.List;
import java.util.Optional;

public interface PostAttacheService {
    List<PostAttache> getAllAttaches();
    Optional<PostAttache> getAttacheById(Long id);
    void deleteAttache(Long id);
    List<PostAttache> getAttachesByPostId(Long postId);
    void deleteAttachesByPostId(Long postId);
}