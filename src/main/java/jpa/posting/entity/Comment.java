package jpa.posting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    // 여러개의 댓글(Many)은 하나의 게시글(One)에 속한다.
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 설정
    @JoinColumn(name = "POST_ID") // DB 테이블의 외래 키(FK) 칼럼명 설정
    private Post post; // 연관관계의 주인
}
