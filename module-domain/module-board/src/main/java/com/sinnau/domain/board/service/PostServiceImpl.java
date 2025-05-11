package com.sinnau.domain.board.service;

import com.sinnau.domain.board.model.entity.Post;
import com.sinnau.domain.board.model.entity.PostKeyword;
import com.sinnau.domain.board.exception.PostNotFoundException;
import com.sinnau.domain.board.repository.PostKeywordRepository;
import com.sinnau.domain.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostKeywordRepository postKeywordRepository;
    private final CommentService commentService;
    private final PostAttacheService postAttacheService;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post createPost(Post post) {
        Post newPost = Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createdAt(LocalDateTime.now())
                .postKeywords("")
                .build();

        return postRepository.save(newPost);
    }

    @Override
    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id + " not found"));

        Post updatedPost = Post.builder()
                .postId(post.getPostId())
                .title(postDetails.getTitle())
                .content(postDetails.getContent())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .postKeywords("")
                .build();

        return postRepository.save(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id + " not found"));
        commentService.deleteCommentsByPostId(id);
        postAttacheService.deleteAttachesByPostId(id);
        postRepository.delete(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getPostsByAuthor(String author) {
        return postRepository.findByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> searchPostsByTitle(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.findByKeywordsContaining(keyword);
    }

    @Override
    public Post addKeywordToPost(Long postId, String keyword) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId + " not found"));

        post.addKeyword(keyword);
        return postRepository.save(post);
    }

    @Override
    public Post removeKeywordFromPost(Long postId, String keyword) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId + " not found"));

        post.removeKeyword(keyword);
        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> getPostKeywords(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId + " not found"));

        return post.getKeywordValues();
    }
}
