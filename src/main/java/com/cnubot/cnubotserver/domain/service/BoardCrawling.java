package com.cnubot.cnubotserver.domain.service;

import com.cnubot.cnubotserver.domain.entity.Board;
import com.cnubot.cnubotserver.domain.entity.DepthSecond;
import com.cnubot.cnubotserver.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.io.IOException;

/**
 * 메뉴별 dom tree구조가 다름
 * getByTag ->  NEWS  ACADEMIC_INFO EDUCATION_INFO  BUSINESS_GROUP_STARTUP
 * UNIV_CULTURE_FIELD CNU_MARKET STUDY_COMPETITION
 * getByTag2 -> RECRUIT_INVITATION    JOB_SEARCH   JOB_OFFER
 * getByTag3 -> STUDENT_RECRUIT, EVENT, JOB_CAREER, FORM_DATA
 * getByTitle -> CNU_NEWS
 */
@Service
@RequiredArgsConstructor
public class BoardCrawling {

    private final BoardRepository repository;

    /**
     * 크롤링 start
     */

    public void process() {
        DepthSecond[] values = DepthSecond.values();
        for(DepthSecond value : values){
            getBoard(value);
        }
    }

    /**
     * 각 메뉴별로 DOM Tree형식에 맞는 Crawling
     */

    private void getBoard(DepthSecond menu){
        Document document = getDoc(menu.getPort());
        Elements elements = new Elements();
        Board board = new Board();
        int elementsSize = elements.size();

        if(menu.ordinal()<7){
            elements = document.select("td");
            for (int i = 0; i < elementsSize - 6; i += 6) {
                String boardUrl = elements.get(i + 1).childNode(0).attr("href");
                board = Board.builder()
                        .menu(menu)
                        .boardNum(elements.get(i).text())
                        .date(elements.get(i + 3).text())
                        .writer(elements.get(i + 2).text())
                        .hits(elements.get(i + 4).text())
                        .url(boardUrl)
                        .name(elements.get(i + 1).text())
                        .build();
                repository.save(board);
            }
        }
        else if(menu.ordinal()<10){
            elements = document.select("td");
            for (int i = 0; i < elementsSize - 6; i += 6) {
                String boardUrl = elements.get(i + 1).childNode(0).attr("href");
                board = Board.builder()
                        .menu(menu)
                        .boardNum(elements.get(i).text())
                        .date(elements.get(i + 4).text())
                        .writer(elements.get(i + 2).text())
                        .hits(elements.get(i + 5).text())
                        .url(boardUrl)
                        .name(elements.get(i + 1).text())
                        .period(elements.get(i + 3).text())
                        .build();
                repository.save(board);
            }
        }
        else if(menu.ordinal()==10){
            elements = document.select(".bodo_listThum");

            for (int i = 0; i < elementsSize; i++) {
                String boardUrl = elements.get(i).child(0).attr("href");
                board = Board.builder()
                        .menu(menu)
                        .date(replace(elements.get(i).getElementsByTag("li").get(1).text()))
                        .writer(replace(elements.get(i).getElementsByTag("li").get(0).text()))
                        .hits(elements.get(i).getElementsByTag("li").get(2).text())
                        .url(boardUrl)
                        .name(elements.get(i).getElementsByTag("h4").text())
                        .picUrl(elements.get(i).child(0).child(0).attr("src"))
                        .build();
                repository.save(board);
            }
        }
        else {
            elements = document.select("tr");
            for (int i = 1; i < elements.size(); i++) {
                Element element = elements.get(i);
                String boardUrl = menu.getPort() + element.getElementsByTag("a").attr("href");
                board = Board.builder()
                        .menu(menu)
                        .boardNum(element.getElementsByClass("b-num-box").text())
                        .date(element.getElementsByClass("b-date").text())
                        .writer(element.getElementsByClass("b-writer").text())
                        .hits(element.getElementsByClass("hit").text().split(" ")[1])
                        .url(boardUrl)
                        .name(element.getElementsByTag("a").text())
                        .build();
                repository.save(board);
            }
        }
    }

    /**
     * Jsoup Lib을 이용해
     * Enum타입의 속성 Port로 urlconnect 시도
     */

    private Document getDoc(String url) {
        Connection conn = Jsoup.connect(url);
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private String replace(String text) {
        String[] texts = text.split(" ");
        return texts[2];
    }

    public String getViewDetail(String boardUrl){
        Document document = getDoc(boardUrl);
        Elements element = document.getElementsByClass("board_viewDetail");
        return element.text();
    }

}
