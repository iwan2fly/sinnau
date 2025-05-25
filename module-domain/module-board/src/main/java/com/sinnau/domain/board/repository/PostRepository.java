package com.sinnau.domain.board.repository;

import com.sinnau.domain.board.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(String author);
    List<Post> findByTitleContaining(String keyword);

    @Query("SELECT p FROM Post p JOIN p.postKeywords k WHERE k.keyword LIKE %:keyword%")
    List<Post> findByKeywordsContaining(@Param("keyword") String keyword);

}
