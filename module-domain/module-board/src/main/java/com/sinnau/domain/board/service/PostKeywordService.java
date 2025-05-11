package com.sinnau.domain.board.service;

import com.sinnau.domain.board.entity.PostKeyword;

import java.util.List;
import java.util.Optional;

public interface PostKeywordService {
    List<PostKeyword> getAllKeywords();
    List<PostKeyword> getKeywordsByPostId(Long postId);
    List<PostKeyword> getPostsByKeyword(String keyword);
    Optional<PostKeyword> getKeywordById(Long id);
    PostKeyword createKeyword(PostKeyword postKeyword);
    void deleteKeyword(Long id);
    void deleteKeywordsByPostId(Long postId);
}