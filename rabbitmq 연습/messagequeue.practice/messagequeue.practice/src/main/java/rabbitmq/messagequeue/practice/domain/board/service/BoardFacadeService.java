package rabbitmq.messagequeue.practice.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rabbitmq.messagequeue.practice.domain.board.transfer.object.SavingBoard;

@Service
@RequiredArgsConstructor
public class BoardFacadeService {

    private final BoardService boardService;

    public void queuingToMessageQueue(SavingBoard savingBoard) {
        boardService.queuingToMessageQueue(savingBoard);
    }

    public void save(SavingBoard savingBoard) {
        boardService.save(savingBoard);
    }
}
