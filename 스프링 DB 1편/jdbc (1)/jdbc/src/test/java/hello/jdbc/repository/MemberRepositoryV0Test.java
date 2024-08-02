package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository=new MemberRepositoryV0();
    @Test
    void crud() throws Exception {
        //create
        Member memberV0 = new Member("memberV0", 10000);
        Member memberV100 = new Member("memberV100", 10000000);
        repository.save(memberV0);
        repository.save(memberV100);

        //read
        Member findMember = repository.findById(memberV0.getMemberId());
        log.info("findMember={}", findMember);
        assertThat(findMember).isEqualTo(memberV0);

        //update
        repository.update("memberV0", 20000);
        Member findMember2 = repository.findById(memberV0.getMemberId());
        assertThat(findMember2.getMoney()).isEqualTo(20000);

        //delete
        repository.delete(memberV100.getMemberId());
        repository.delete(memberV0.getMemberId());
        assertThrows(NoSuchElementException.class, () -> repository.findById(memberV100.getMemberId()));
    }

}