package jpa.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
public class PostCreateResponse {
    private Long id;
    private String title;
    private String content;
    private String message;
}