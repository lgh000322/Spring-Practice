package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);
        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(member.getId());
        assertEquals(findMember,savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("kim", 10);
        Member member2 = new Member("lee", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> findMembers = memberRepository.findAll();

        //then
        assertEquals(findMembers.size(),2);
        org.assertj.core.api.Assertions.assertThat(findMembers).contains(member1, member2);

    }


    @Test
    void findById() {
        //given
        Member member = new Member("kim", 10);
        memberRepository.save(member);

        //when
        Member findMember = memberRepository.findById(member.getId());

        //then
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}