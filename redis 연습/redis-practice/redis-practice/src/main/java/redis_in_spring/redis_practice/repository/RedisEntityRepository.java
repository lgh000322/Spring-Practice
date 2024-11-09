package redis_in_spring.redis_practice.repository;

import org.springframework.data.repository.CrudRepository;
import redis_in_spring.redis_practice.entity.RedisEntity;

public interface RedisEntityRepository extends CrudRepository<RedisEntity, Long> {
}
