package jpa.posting.controller;


import jpa.posting.dto.comment.CommentCreateRequest;
import jpa.posting.dto.post.PostCreateRequest;
import jpa.posting.dto.post.PostCreateResponse;
import jpa.posting.dto.post.PostDetailResponse;
import jpa.posting.dto.post.PostListResponse;
import jpa.posting.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public PostCreateResponse createPost(@RequestBody PostCreateRequest dto) {
        return boardService.createPost(dto);
    }

    @GetMapping
    public List<PostListResponse> getAllPosts() {
        return boardService.getPosts();
    }

    @GetMapping("/{postId}")
    public PostDetailResponse getPostById(@PathVariable Long postId) {
        return boardService.getPost(postId);
    }

    @PostMapping("/{postId}/comments")
    public String addComment(
            @PathVariable Long postId,
            @RequestBody CommentCreateRequest dto
            ) {
        boardService.addComment(postId, dto);
        return postId + "번 게시물에 댓글이 등록되었습니다.";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId) {
        boardService.deletePost(postId);
        return postId + "번 게시물이 삭제되었습니다.";
    }
}
