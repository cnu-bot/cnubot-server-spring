package com.cnubot.cnubotserver.board.service;

import com.cnubot.cnubotserver.board.entity.Board;
import com.cnubot.cnubotserver.board.entity.DepthSecond;
import com.cnubot.cnubotserver.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        Document document = getDoc(menu.getPort());
        Elements elements;
        Board board;
        if(menu.ordinal()<7){
            elements = document.select("td");
            for (int i = 0; i <  elements.size() - 6; i += 6) {
                String boardUrl = "https://plus.cnu.ac.kr/_prog/_board"
                        + elements.get(i + 1).childNode(0).attr("href").replace(".", "");
                board = Board.builder()
                        .menu(menu)
                        .boardNum(elements.get(i).text())
                        .date(elements.get(i + 3).text())
                        .writer(elements.get(i + 2).text())
                        .hits(elements.get(i + 4).text())
                        .url(boardUrl)
                        .name(elements.get(i + 1).text())
                        .boardDetail(getViewDetail(boardUrl))
                        .build();
                repository.save(board);
            }
        }
        else if(menu.ordinal()<10){
            elements = document.select("td");
            for (int i = 0; i < elements.size() - 6; i += 6) {
                String boardUrl;
                if(menu.ordinal() == 7){
                    boardUrl = "https://plus.cnu.ac.kr/_prog/_board"
                            + elements.get(i + 1).childNode(0).attr("href").replace(".", "");
                }
                else{
                    boardUrl = "https://plus.cnu.ac.kr"
                            + elements.get(i + 1).childNode(0).attr("href");
                }
                board = Board.builder()
                        .menu(menu)
                        .boardNum(elements.get(i).text())
                        .date(elements.get(i + 4).text())
                        .writer(elements.get(i + 2).text())
                        .hits(elements.get(i + 5).text())
                        .url(boardUrl)
                        .name(elements.get(i + 1).text())
                        .period(elements.get(i + 3).text())
                        .boardDetail(getViewDetail(boardUrl))
                        .build();
                repository.save(board);
            }
        }
        else if(menu.ordinal()==10){
            elements = document.select(".bodo_listThum");
            for (int i = 0; i <  elements.size(); i++) {
                String boardUrl = "https://plus.cnu.ac.kr/_prog/_board"
                        + elements.get(i).child(0).attr("href").replace(".","");
                board = Board.builder()
                        .menu(menu)
                        .date(replace(elements.get(i).getElementsByTag("li").get(1).text()))
                        .writer(replace(elements.get(i).getElementsByTag("li").get(0).text()))
                        .hits(elements.get(i).getElementsByTag("li").get(2).text())
                        .url(boardUrl)
                        .name(elements.get(i).getElementsByTag("h4").text())
                        .picUrl(elements.get(i).child(0).child(0).attr("src"))
                        .boardDetail(getViewDetail(boardUrl))
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
                        .boardDetail(getViewDetail(boardUrl))
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
        if(element.text().isEmpty()){
            element = document.getElementsByClass("left_viewDetail");
        }
        if(boardUrl.startsWith("https://cnuint.cnu.ac.kr")){
            element = document.getElementsByClass("fr-view");
        }
        String boardDetail = element.text();
        return boardDetail;
    }

}
