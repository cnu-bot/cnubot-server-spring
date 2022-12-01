package com.cnubot.cnubotserver.crawling;


import com.cnubot.cnubotserver.board.service.BoardCrawling;
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

    @Test
    void 게시글내용추출테스트(){
        String result =
                boardCrawling.getViewDetail("https://plus.cnu.ac.kr/_prog/_board/?mode=V&no=2489272&code=sub07_0703&site_dvs_cd=kr&menu_dvs_cd=0703&skey=&sval=&site_dvs=&ntt_tag=&GotoPage=");
        System.out.println(result);
    }
}
