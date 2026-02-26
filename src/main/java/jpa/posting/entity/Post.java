package jpa.posting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor // 기본 생성자 필수
public class Post {

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID를 자동 생성 (AutoIncrement)
    private Long id;

    @Column(nullable = false) // 칼럼 설정 (null 불허)
    private String title;

    @Lob // 긴 텍스트를 위한 설정
    private String content;

    // 하나의 게시글(One)에는 여러개의 댓글(Many)이 속한다.
    // mappedBy = "post" : 나는 주인이 아니다.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL) // 게시글을 저장할 때 댓글도 같이 저장하는 옵셥
    private List<Comment> comments = new ArrayList<>();
}
