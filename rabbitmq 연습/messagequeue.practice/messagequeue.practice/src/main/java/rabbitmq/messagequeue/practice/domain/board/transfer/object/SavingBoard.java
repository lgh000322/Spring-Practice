package rabbitmq.messagequeue.practice.domain.board.transfer.object;

import lombok.Builder;

@Builder
public record SavingBoard(
        String content,
        String title,
        Integer view,
        Integer recommended
) {
}
