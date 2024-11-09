package redis_in_spring.redis_practice.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@RedisHash(value = "redisEntity", timeToLive = 30)
@ToString
@Getter
public class RedisEntity {

    @Id
    private Long id;

    private String text;

    private LocalDateTime now;

}
