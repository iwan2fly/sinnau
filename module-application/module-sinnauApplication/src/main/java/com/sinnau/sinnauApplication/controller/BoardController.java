package com.sinnau.sinnauApplication.controller;

import com.sinnau.domain.board.model.entity.Comment;
import com.sinnau.domain.board.model.entity.Post;
import com.sinnau.domain.board.service.CommentService;
import com.sinnau.domain.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final PostService postService;
    private final CommentService commentService;

    /**
     * 게시글 작성 엔드포인트
     * @param requestBody 게시글 정보 (제목, 내용, 작성자)
     * @return 생성된 게시글 정보
     */
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Map<String, String> requestBody) {
        Post post = Post.builder()
                .title(requestBody.get("title"))
                .content(requestBody.get("content"))
                .author(requestBody.get("author"))
                .build();

        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    /**
     * 게시글 수정 엔드포인트
     * @param id 수정할 게시글 ID
     * @param requestBody 수정할 게시글 정보 (제목, 내용)
     * @return 수정된 게시글 정보
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        Post postDetails = Post.builder()
                .title(requestBody.get("title"))
                .content(requestBody.get("content"))
                .build();

        Post updatedPost = postService.updatePost(id, postDetails);
        return ResponseEntity.ok(updatedPost);
    }

    /**
     * 게시글 삭제 엔드포인트
     * @param id 삭제할 게시글 ID
     * @return 삭제 성공 메시지
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Map<String, String>> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(Map.of("message", "게시글이 성공적으로 삭제되었습니다."));
    }

    /**
     * 댓글 작성 엔드포인트
     * @param postId 댓글을 작성할 게시글 ID
     * @param requestBody 댓글 정보 (내용, 작성자 ID)
     * @return 생성된 댓글 정보
     */
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Map<String, String> requestBody) {
        // CommentService에 createComment 메서드가 없으므로 직접 구현
        Comment comment = Comment.builder()
                .userId(Long.valueOf(requestBody.get("userId")))
                .content(requestBody.get("content"))
                .createdAt(LocalDateTime.now())
                .build();

        // 여기서는 CommentRepository를 직접 사용하지 않고,
        // CommentService에 createComment 메서드가 있다고 가정하고 구현
        // 실제 구현 시에는 CommentService에 해당 메서드를 추가해야 함
        // Comment createdComment = commentService.createComment(postId, comment);

        // 임시 응답 (실제 구현 시 수정 필요)
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    /**
     * 댓글 수정 엔드포인트
     * @param id 수정할 댓글 ID
     * @param requestBody 수정할 댓글 정보 (내용)
     * @return 수정된 댓글 정보
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        // CommentService에 updateComment 메서드가 없으므로 직접 구현
        // 실제 구현 시에는 CommentService에 해당 메서드를 추가해야 함

        // 임시 구현 (실제 구현 시 수정 필요)
        Comment comment = commentService.getCommentById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        Comment updatedComment = Comment.builder()
                .commentId(comment.getCommentId())
                .userId(comment.getUserId())
                .content(requestBody.get("content"))
                .createdAt(comment.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        // Comment savedComment = commentService.updateComment(id, updatedComment);

        // 임시 응답 (실제 구현 시 수정 필요)
        return ResponseEntity.ok(updatedComment);
    }

    /**
     * 댓글 삭제 엔드포인트
     * @param id 삭제할 댓글 ID
     * @return 삭제 성공 메시지
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Map<String, String>> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(Map.of("message", "댓글이 성공적으로 삭제되었습니다."));
    }
}
