package com.sinnau.domain.board.service;

import com.sinnau.domain.board.model.entity.PostAttache;
import com.sinnau.domain.board.repository.PostAttacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostAttacheServiceImpl implements PostAttacheService {

    private final PostAttacheRepository postAttacheRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostAttache> getAllAttaches() {
        return postAttacheRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostAttache> getAttacheById(Long id) {
        return postAttacheRepository.findById(id);
    }

    @Override
    public void deleteAttache(Long id) {
        postAttacheRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostAttache> getAttachesByPostId(Long postId) {
        return postAttacheRepository.findByPostPostId(postId);
    }

    @Override
    public void deleteAttachesByPostId(Long postId) {
        postAttacheRepository.deleteByPostPostId(postId);
    }
}
