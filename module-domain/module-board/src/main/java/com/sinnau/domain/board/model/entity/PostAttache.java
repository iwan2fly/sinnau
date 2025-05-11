package com.sinnau.domain.board.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "postAttaches")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostAttache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postAttacheId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}