package rabbitmq.messagequeue.practice.global.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rabbitmq.messagequeue.practice.domain.board.service.BoardFacadeService;
import rabbitmq.messagequeue.practice.domain.board.service.BoardService;
import rabbitmq.messagequeue.practice.domain.board.transfer.object.SavingBoard;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQSubscriber {


    private final BoardFacadeService boardFacadeService;

    @RabbitListener(queues = "chat.storage.queue")
    public void handleRabbitMQ(Message message) throws IOException {
        log.info("큐의 이벤트 처리");
        byte[] body = message.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        SavingBoard savingBoard = objectMapper.readValue(body, SavingBoard.class);
        boardFacadeService.save(savingBoard);
    }
}
