package rabbitmq.messagequeue.practice.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitmq.messagequeue.practice.domain.board.service.BoardFacadeService;
import rabbitmq.messagequeue.practice.domain.board.transfer.object.SavingBoard;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardFacadeService boardFacadeService;

    @GetMapping("/save")
    public String save() {
        // 게시글 데이터를 받았다고 가정
        SavingBoard savingBoard = SavingBoard.builder()
                .content("예시 내용")
                .title("예시 주제")
                .recommended(0)
                .view(0)
                .build();

        boardFacadeService.queuingToMessageQueue(savingBoard);

        return "ok";
    }
}
