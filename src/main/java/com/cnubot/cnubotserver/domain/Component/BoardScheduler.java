package com.cnubot.cnubotserver.domain.Component;

import com.cnubot.cnubotserver.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
