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
        Document document = getDoc(menu);
        Elements elements = new Elements();
        Board board = new Board();
        int elementsSize = elements.size();

        if(menu.ordinal()<7){
            elements = document.select("td");
            for (int i = 0; i < elementsSize - 6; i += 6) {
                board.setMenu(menu);
                board.setBoardNum(elements.get(i).text()); //번호
                board.setUrl(elements.get(i + 1).childNode(0).attr("href")); //url
                board.setName(elements.get(i + 1).text()); //제목
                board.setWriter(elements.get(i + 2).text()); //적송저
                board.setDate(elements.get(i + 3).text()); //작성일
                board.setHits(elements.get(i + 4).text()); //조회수
                repository.save(board);
            }
        }
        else if(menu.ordinal()<10){
            elements = document.select("td");
            for (int i = 0; i < elementsSize - 6; i += 6) {
                board.setMenu(menu);
                board.setBoardNum(elements.get(i).text()); //번호
                board.setUrl(elements.get(i + 1).childNode(0).attr("href")); //url
                board.setName(elements.get(i + 1).text()); //제목
                board.setWriter(elements.get(i + 2).text()); //작성자
                board.setPeriod(elements.get(i + 3).text()); //모집기한
                board.setDate(elements.get(i + 4).text()); //작성일
                board.setHits(elements.get(i + 5).text()); //조회수
                repository.save(board);
            }
        }
        else if(menu.ordinal()==10){
            elements = document.select(".bodo_listThum");

            for (int i = 0; i < elementsSize; i++) {
                board.setMenu(menu);
                board.setUrl(elements.get(i).child(0).attr("href"));
                board.setPicUrl(elements.get(i).child(0).child(0).attr("src"));
                board.setName(elements.get(i).getElementsByTag("h4").text());
                board.setBoardNum("");
                board.setWriter(replace(elements.get(i).getElementsByTag("li").get(0).text()));
                board.setDate(replace(elements.get(i).getElementsByTag("li").get(1).text()));
                board.setHits(elements.get(i).getElementsByTag("li").get(2).text());
                repository.save(board);
            }
        }
        else {
            elements = document.select("tr");
            for (int i = 1; i < elements.size(); i++) {
                board.setMenu(menu);
                Element element = elements.get(i);
                board.setBoardNum(element.getElementsByClass("b-num-box").text());
                board.setDate(element.getElementsByClass("b-date").text());
                board.setWriter(element.getElementsByClass("b-writer").text());
                board.setHits(element.getElementsByClass("hit").text().split(" ")[1]);
                board.setUrl(menu.getPort() + element.getElementsByTag("a").attr("href"));
                board.setName(element.getElementsByTag("a").text());
                repository.save(board);
            }
        }
    }

    /**
     * Jsoup Lib을 이용해
     * Enum타입의 속성 Port로 urlconnect 시도
     */

    private Document getDoc(DepthSecond menu) {
        Connection conn = Jsoup.connect(menu.getPort());
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


}
