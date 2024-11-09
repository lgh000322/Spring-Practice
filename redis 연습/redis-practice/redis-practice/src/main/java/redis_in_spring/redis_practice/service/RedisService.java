package redis_in_spring.redis_practice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis_in_spring.redis_practice.entity.RedisEntity;
import redis_in_spring.redis_practice.repository.RedisEntityRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RedisService {

    private final RedisEntityRepository redisEntityRepository;


    @Transactional
    public RedisEntity save(RedisEntity redisEntity) {
        RedisEntity save = redisEntityRepository.save(redisEntity);
        return save;
    }

    public RedisEntity findById(Long id) {
        RedisEntity redisEntity = redisEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("찾을 수 없음"));

        return redisEntity;
    }
}
