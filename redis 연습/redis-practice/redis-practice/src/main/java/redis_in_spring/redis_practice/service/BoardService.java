package redis_in_spring.redis_practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis_in_spring.redis_practice.entity.Board;
import redis_in_spring.redis_practice.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Cacheable(
            cacheNames = "getBoards",
            key = "'boards:page:' + #p0 + ':size:' + #p1",
            cacheManager = "boardCacheManager"
    )
    public List<Board> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Board> pageOfBoards = boardRepository.findAllByOrderByCreatedAtDesc(pageable);
        return pageOfBoards.getContent();
    }

    @Transactional
    public Board save() {
        Board board=Board.builder()
                .title("예시 주제1")
                .content("예시 내용1")
                .build();

        return boardRepository.save(board);
    }


}
