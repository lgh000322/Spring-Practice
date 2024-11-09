package redis_in_spring.redis_practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis_in_spring.redis_practice.entity.Board;
import redis_in_spring.redis_practice.entity.RedisEntity;
import redis_in_spring.redis_practice.service.BoardService;
import redis_in_spring.redis_practice.service.RedisService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisService redisService;
    private static AtomicLong redisId = new AtomicLong(1L);

    @GetMapping("/boards")
    public List<Board> getBoards(@RequestParam(defaultValue = "1", name = "page") int page,
                                 @RequestParam(defaultValue = "10", name = "size") int size) {
        log.info("page={}, size={}", page, size);
        return boardService.getBoards(page, size);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }

    @GetMapping("/redis/test/add")
    public String redisTest() {
        String key = "redis:test:EX";


        //게시글 데이터를 넘겨받고 저장한다고 가정함.
        Board savedBoard = boardService.save();

        redisTemplate.opsForValue().set(key, savedBoard);
        return "redis 저장 완료";
    }

    @GetMapping("/redis/test/add/ttl")
    public String redisSaveTTL() {
        String key = "redis:test:EX:TTL";
        redisTemplate.opsForValue().set(key, "ttl Test", Duration.ofSeconds(60));
        return "ok";
    }

    @GetMapping("/redis/test/get")
    public Object redisGetTest() {
        String key = "redis:test:EX";

        Object redisResult = redisTemplate.opsForValue().get(key);
        Class<?> clazz = Board.class;

        if (redisResult != null) {
            return clazz.cast(redisResult);
        }

        return "ok";

    }

    @GetMapping("/redis/repository/save")
    public RedisEntity redisRepositorySave() {
        RedisEntity redisEntity = RedisEntity.builder()
                .id(redisId.get())
                .text("예시 텍스트")
                .now(LocalDateTime.now())
                .build();

        RedisEntity save = redisService.save(redisEntity);
        log.info("save={}",save.toString());
        increaseAtomicLong();

        return save;
    }

    @GetMapping("/redis/repository/find")
    public RedisEntity redisRepositoryFind(@RequestParam(name = "id") Long id) {
        RedisEntity foundedRedisEntity = redisService.findById(id);
        log.info("founded={}", foundedRedisEntity.toString());
        return foundedRedisEntity;
    }

    private void increaseAtomicLong() {
        redisId.set(redisId.get() + 1);
    }
}
