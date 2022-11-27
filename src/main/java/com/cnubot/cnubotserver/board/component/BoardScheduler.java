package com.cnubot.cnubotserver.board.component;

import com.cnubot.cnubotserver.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardScheduler {

    private final BoardService boardService;

    // cron 방식으로 스케쥴링 1시간 간격으로 refresh
    @Scheduled(cron = "0 0 12 1/1 * ? * ")
    public void refresh(){
        boardService.refreshBoards();
    }
}
