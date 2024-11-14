package rabbitmq.messagequeue.practice.domain.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rabbitmq.messagequeue.practice.domain.board.entity.Board;
import rabbitmq.messagequeue.practice.domain.board.repository.BoardRepository;
import rabbitmq.messagequeue.practice.domain.board.transfer.object.SavingBoard;
import rabbitmq.messagequeue.practice.global.queue.RabbitMQPublisher;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final RabbitMQPublisher rabbitMQPublisher;
    private final BoardRepository boardRepository;

    public void queuingToMessageQueue(SavingBoard savingBoard) {
        log.info("큐에 이벤트 발행");
        rabbitMQPublisher.sendMessage(savingBoard);
    }

    @Transactional
    public void save(SavingBoard savingBoard) {
        Board board=Board.builder()
                .title(savingBoard.title())
                .content(savingBoard.content())
                .view(savingBoard.view())
                .recommended(savingBoard.recommended())
                .build();

        boardRepository.save(board);
    }

}
