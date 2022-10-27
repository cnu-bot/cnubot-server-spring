package com.cnubot.cnubotserver.crawling;


import com.cnubot.cnubotserver.domain.service.BoardCrawling;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrawlingTest {
    @Autowired
    BoardCrawling boardCrawling;

    @Test
    void 크롤링테스트(){
        boardCrawling.process();
    }

}
