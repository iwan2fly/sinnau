package com.sinnau.domain.board.service;

import com.sinnau.domain.board.entity.Post;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post createPost(Post post);
    Post updatePost(Long id, Post postDetails);
    void deletePost(Long id);
    List<Post> getPostsByAuthor(String author);
    List<Post> searchPostsByTitle(String keyword);
    List<Post> searchPostsByKeyword(String keyword);
    Post addKeywordToPost(Long postId, String keyword);
    Post removeKeywordFromPost(Long postId, String keyword);
    Set<String> getPostKeywords(Long postId);

}
