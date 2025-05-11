package com.sinnau.domain.board.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "postKeywords")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postKeywordId;

    @Column(nullable = false)
    private String keywordType;

    @Column(nullable = false)
    private String keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;
}