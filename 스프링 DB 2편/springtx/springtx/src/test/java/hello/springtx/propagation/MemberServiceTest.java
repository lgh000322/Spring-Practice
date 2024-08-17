package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository logRepository;
    
    @Test
    public void outerTransactionalOff_success() throws Exception{
        //given
        String username = "outerTransactionalOff_success";
        //when
        memberService.joinV1(username);
        
        //then: 모든 데이터가 정상 저장
        assertTrue(memberRepository.findByUsername(username).isPresent());
        assertTrue(logRepository.findByMessage(username).isPresent());
    }

    @Test
    public void outerTransactionalOff_fail() throws Exception{
        //given
        String username = "로그 예외_outerTransactionalOff_fail";
        //when
        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        //then: 로그 데이터만 롤백
        assertTrue(memberRepository.findByUsername(username).isPresent());
        assertTrue(logRepository.findByMessage(username).isEmpty());
    }

    @Test
    public void single_transaction() throws Exception{
        //given
        String username = "outerTransactionalOff_success";
        //when
        memberService.joinV1(username);

        //then: 모든 데이터가 정상 저장
        assertTrue(memberRepository.findByUsername(username).isPresent());
        assertTrue(logRepository.findByMessage(username).isPresent());
    }

    @Test
    public void outerTransaction_success() throws Exception{
        //given
        String username = "outerTransaction_success";

        //when
        memberService.joinV1(username);

        //then: 모든 데이터가 정상 저장
        assertTrue(memberRepository.findByUsername(username).isPresent());
        assertTrue(logRepository.findByMessage(username).isPresent());
    }

    @Test
    public void outerTransactionalOn_fail() throws Exception{
        //given
        String username = "로그 예외_outerTransactionalOn_fail";
        //when
        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        //then: 모든 데이터가 롤백
        assertTrue(memberRepository.findByUsername(username).isEmpty());
        assertTrue(logRepository.findByMessage(username).isEmpty());
    }

    @Test
    public void recoverException_fail() throws Exception{
        //given
        String username = "로그 예외_recoverException_fail";
        //when
        assertThatThrownBy(() -> memberService.joinV2(username))
                .isInstanceOf(UnexpectedRollbackException.class);

        //then: 모든 데이터가 롤백
        assertTrue(memberRepository.findByUsername(username).isEmpty());
        assertTrue(logRepository.findByMessage(username).isEmpty());
    }

    @Test
    public void recoverException_failCatch() throws Exception{
        //given
        String username = "로그 예외_recoverException_failCatch";
        //when
        memberService.joinV2(username);

        //then: 회원 저장, 로그 롤백
        assertTrue(memberRepository.findByUsername(username).isPresent());
        assertTrue(logRepository.findByMessage(username).isEmpty());
    }
}