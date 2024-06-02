package study.querydsl.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.querydsl.entity.Member;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long>,MemberRepositoryCustom{
    List<Member> findByUsername(String username);

}
