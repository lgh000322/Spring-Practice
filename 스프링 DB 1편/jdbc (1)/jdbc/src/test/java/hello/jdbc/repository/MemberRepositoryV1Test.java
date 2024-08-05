package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class MemberRepositoryV1Test {
    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);

        repository = new MemberRepositoryV1(dataSource);
    }

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