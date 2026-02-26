package jpa.posting.repository;

import jpa.posting.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


// JPA는 인터페이스만 만들면 데이터를 저장하고 조회하는 기능을 자동으로 완성
// <엔티티 타입, ID 타입>을 넣어주면 끝!
public interface PostRepository extends JpaRepository<Post, Long> {
}
