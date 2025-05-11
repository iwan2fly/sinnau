package com.sinnau.domain.board.service;

import com.sinnau.domain.board.entity.PostKeyword;
import com.sinnau.domain.board.exception.PostKeywordNotFoundException;
import com.sinnau.domain.board.repository.PostKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostKeywordServiceImpl implements PostKeywordService {

    private final PostKeywordRepository postKeywordRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostKeyword> getAllKeywords() {
        return postKeywordRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostKeyword> getKeywordsByPostId(Long postId) {
        return postKeywordRepository.findByPostPostId(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostKeyword> getPostsByKeyword(String keyword) {
        return postKeywordRepository.findByKeyword(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostKeyword> getKeywordById(Long id) {
        return postKeywordRepository.findById(id);
    }

    @Override
    public PostKeyword createKeyword(PostKeyword postKeyword) {
        return postKeywordRepository.save(postKeyword);
    }

    @Override
    public void deleteKeyword(Long id) {
        PostKeyword postKeyword = postKeywordRepository.findById(id)
                .orElseThrow(() -> new PostKeywordNotFoundException("PostKeyword with id " + id + " not found"));
        postKeywordRepository.delete(postKeyword);
    }

    @Override
    public void deleteKeywordsByPostId(Long postId) {
        postKeywordRepository.deleteByPostPostId(postId);
    }
}