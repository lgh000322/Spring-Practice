package redis_in_spring.redis_practice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JackSonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // LocalDateTime 등 Java 8 날짜와 시간을 처리할 수 있도록 JavaTimeModule 등록
        mapper.registerModule(new JavaTimeModule());

        // Timestamp 형식 대신 ISO 8601 형식(예: 2024-11-09T15:30:00)을 사용하도록 설정
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }
}
