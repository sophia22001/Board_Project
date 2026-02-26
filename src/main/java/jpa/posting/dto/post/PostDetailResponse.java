package jpa.posting.dto.post;

import jpa.posting.dto.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostDetailResponse {
    private Long id;
    private String title;
    private String content;
    private List<CommentResponse> comments;
}
