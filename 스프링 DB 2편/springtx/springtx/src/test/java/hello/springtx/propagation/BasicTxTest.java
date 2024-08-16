package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

@SpringBootTest
@Slf4j
public class BasicTxTest {

    @Autowired
    PlatformTransactionManager txManager;

    @TestConfiguration
    static class BasicTxTestConfig{
        @Bean
        public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
            return new DataSourceTransactionManager(dataSource);
        }
    }

    @Test
    public void commit() throws Exception{
        log.info("트랜잭션 시작");
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 커밋 시작");
        txManager.commit(status);
        log.info("트랜잭션 커밋 완료");
    }
    @Test
    public void rollback() throws Exception{
        log.info("트랜잭션 시작");
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 롤백 시작");
        txManager.rollback(status);
        log.info("트랜잭션 롤백 완료");
    }

    @Test
    public void doubleCommit() throws Exception{
        log.info("트랜잭션1 시작");
        TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 1 커밋");
        txManager.commit(tx1);

        log.info("트랜잭션 2 시작");
        TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 커밋");
        txManager.commit(tx2);

    }

    @Test
    public void doubleCommitRollback() throws Exception{
        log.info("트랜잭션1 시작");
        TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 1 커밋");
        txManager.commit(tx1);

        log.info("트랜잭션 2 시작");
        TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션 롤백");
        txManager.rollback(tx2);
    }
    
    @Test
    public void inner_commit() throws Exception{
        log.info("외부 트랜잭션 시작");
        TransactionStatus outerTx = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outerTx={}", outerTx.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        TransactionStatus innerTx = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("innerTx={}", innerTx.isNewTransaction());
        log.info("내부 트랜잭션 커밋");
        txManager.commit(innerTx);

        log.info("외부 트랜잭션 커밋");
        txManager.commit(outerTx);

    }
    @Test
    public void outer_rollback() throws Exception{
        log.info("외부 트랜잭션 시작");
        TransactionStatus outerTx = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outerTx={}", outerTx.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        TransactionStatus innerTx = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("innerTx={}", innerTx.isNewTransaction());
        log.info("내부 트랜잭션 커밋");
        txManager.commit(innerTx);

        log.info("외부 트랜잭션 롤백");
        txManager.rollback(outerTx);

    }

    @Test
    public void inner_rollback() throws Exception{
        log.info("외부 트랜잭션 시작");
        TransactionStatus outerTx = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outerTx={}", outerTx.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        TransactionStatus innerTx = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("innerTx={}", innerTx.isNewTransaction());
        log.info("내부 트랜잭션 롤백");
        txManager.rollback(innerTx);

        log.info("외부 트랜잭션 커밋");
        Assertions.assertThatThrownBy(() -> txManager.commit(outerTx))
                .isInstanceOf(UnexpectedRollbackException.class);

    }

}
