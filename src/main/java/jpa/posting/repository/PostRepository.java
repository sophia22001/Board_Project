package jpa.posting.repository;

import jpa.posting.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// JPA는 인터페이스만 만들면 데이터를 저장하고 조회하는 기능을 자동으로 완성
// <엔티티 타입, ID 타입>을 넣어주면 끝!
public interface PostRepository extends JpaRepository<Post, Long> {

    // @Query 를 사용해 직접 JPQL을 작성한다.
    // fetch join을 쓰면 연관된 데이터를 한 번의 쿼리로 다 긁어온다.
    @Query("select p from Post p left join fetch p.comments")
    List<Post> findAllPostsWithComments();
}
