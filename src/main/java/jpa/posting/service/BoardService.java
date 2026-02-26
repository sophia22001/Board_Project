package jpa.posting.service;


import jpa.posting.dto.comment.CommentCreateRequest;
import jpa.posting.dto.comment.CommentResponse;
import jpa.posting.dto.post.PostCreateRequest;
import jpa.posting.dto.post.PostCreateResponse;
import jpa.posting.dto.post.PostDetailResponse;
import jpa.posting.dto.post.PostListResponse;
import jpa.posting.entity.Comment;
import jpa.posting.entity.Post;
import jpa.posting.repository.CommentRepository;
import jpa.posting.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public PostCreateResponse createPost(PostCreateRequest dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postRepository.save(post);

        return new PostCreateResponse(post.getId(), post.getTitle(), post.getContent(), "성공적으로 저장되었습니다.");
    }

    // 게시물 전체 조회
    @Transactional(readOnly = true) // 조회 전용일 때 최적화
    public List<PostListResponse> getPosts() {
//        List<Post> posts = postRepository.findAll();
        List<Post> posts = postRepository.findAllPostsWithComments();

        return posts.stream()
                .map(p-> new PostListResponse(
                        p.getId(),
                        p.getTitle(),
                        p.getComments().size()
                )).toList();
    }

    // 게시물 하나 상세 조회
    @Transactional(readOnly = true) // 조회 전용일 때 최적화
    public PostDetailResponse getPost(Long id) {
        // 게시물 찾기
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("게시물이 없습니다."));

        // 댓글 엔티티 리스트를 DTO 리스트로 변환 (지연 로딩 발생 시점)
        List<CommentResponse> commentDtos = post.getComments().stream()
                .map(c-> new CommentResponse(c.getId(), c.getComment()))
                .toList();

        return new PostDetailResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                commentDtos
        );
    }



    // 이미 있는 게시글에 댓글만 추가
    @Transactional
    public void addComment(Long postId, CommentCreateRequest dto){
        // 1. 이미 존재하는 게시글 찾기 (없으면 에러)
        Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("게시글을 찾을 수 없습니다."));

        // 2. 새로운 댓글 생성
        Comment comment = new Comment();
        comment.setComment(dto.getComment());

        // 3. 찾은 게시글과 연결
        // 연관관계 주인 세팅
        comment.setPost(post);

        // 4. 댓글 저장
        commentRepository.save(comment);

    }
}
