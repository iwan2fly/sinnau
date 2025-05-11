package com.sinnau.domain.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PostKeyword> postKeywords = new ArrayList<>();

    // Helper methods for managing keywords
    public void addKeyword(String keyword) {
        PostKeyword postKeyword = PostKeyword.builder()
                .keyword(keyword)
                .post(this)
                .build();
        postKeywords.add(postKeyword);
    }

    public void removeKeyword(String keyword) {
        postKeywords.removeIf(postKeyword -> postKeyword.getKeyword().equals(keyword));
    }

    public Set<String> getKeywordValues() {
        Set<String> keywordValues = new HashSet<>();
        for (PostKeyword postKeyword : postKeywords) {
            keywordValues.add(postKeyword.getKeyword());
        }
        return keywordValues;
    }
}
