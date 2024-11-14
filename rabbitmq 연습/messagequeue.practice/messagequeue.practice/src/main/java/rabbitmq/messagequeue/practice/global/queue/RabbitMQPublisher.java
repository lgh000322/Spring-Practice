package rabbitmq.messagequeue.practice.global.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import rabbitmq.messagequeue.practice.domain.board.transfer.object.SavingBoard;

@Component
@RequiredArgsConstructor
public class RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(SavingBoard savingBoard) {
        rabbitTemplate.convertAndSend("chat.storage.queue", savingBoard);
    }
}
