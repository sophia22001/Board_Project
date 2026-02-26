package jpa.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostListResponse {
    private Long id;
    private String title;
    private int commentCount; // 댓글 개수만 표시
}
