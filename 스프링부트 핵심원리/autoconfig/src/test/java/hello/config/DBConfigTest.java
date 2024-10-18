package hello.config;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class DBConfigTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    TransactionManager txManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void checkBean() {
        log.info("datasource = {}", dataSource);
        log.info("transactionManager = {}", txManager);
        log.info("jdbcTemplate = {}",jdbcTemplate);

        assertThat(dataSource).isNotNull();
        assertThat(txManager).isNotNull();
        assertThat(jdbcTemplate).isNotNull();

    }

}