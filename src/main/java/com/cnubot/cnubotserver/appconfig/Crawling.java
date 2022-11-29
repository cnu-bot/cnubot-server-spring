package com.cnubot.cnubotserver.appconfig;

import com.cnubot.cnubotserver.board.service.BoardCrawling;
import com.cnubot.cnubotserver.foodcourt.service.crawling.FoodCrawling;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Crawling {

    private final BoardCrawling boardCrawling;
    private final FoodCrawling foodCrawling;

    public void process() {
        boardCrawling.process();
        foodCrawling.process();
    }
}
