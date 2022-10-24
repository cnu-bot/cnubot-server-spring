package com.cnubot.cnubotserver.domain.service;

import com.cnubot.cnubotserver.domain.entity.Board;
import com.cnubot.cnubotserver.domain.entity.DepthSecond;
import com.cnubot.cnubotserver.domain.repository.BoardRepository;
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
 *              UNIV_CULTURE_FIELD CNU_MARKET STUDY_COMPETITION
 * getByTag2 -> RECRUIT_INVITATION    JOB_SEARCH   JOB_OFFER
 * getByTag3 -> STUDENT_RECRUIT, EVENT, JOB_CAREER, FORM_DATA
 * getByTitle -> CNU_NEWS
 */
@Service
public class BoardCrawling {
    @Autowired
    BoardRepository repository;


    /**
     * schedular에서 call
     */
    public  void process(){
        DepthSecond[] array= DepthSecond.values();
        for(int i=0; i<7;i++){
            DepthSecond port=array[i];
            getByTag(getDoc(port),port);
        }
        for(int i=7; i<10;i++){
            DepthSecond port=array[i];
            getByTag2(getDoc(port),port);
        }
        for(int i=11; i<15;i++){
            DepthSecond port=array[i];
            getByTag3(getDoc(port),port);
        }
        DepthSecond port = array[10];
        getByClass(getDoc(port),port);


    }

    /**
     * Jsoup Lib을 이용해
     * Enum타입의 속성 Port로 urlconnect 시도
     */
    private Document getDoc(DepthSecond port){
        Connection conn = Jsoup.connect(port.getPort());
        Document document = null;
        try{
            document=conn.get();
        }catch (IOException e){
            e.printStackTrace();
        }
        return document;
    }
        //CNU_NEW전용

    /**
     * Thumbnail 사진이 있는 CNU_NEWS
     */
    private void getByClass(Document document, DepthSecond port){
            Elements nums = document.select(".bodo_listThum");

            for(int i=0; i<nums.size(); i++){
                Board board = new Board();
                board.setMenu(port);
                board.setUrl(nums.get(i).child(0).attr("href"));
                board.setPic_url(nums.get(i).child(0).child(0).attr("src"));
                board.setName(nums.get(i).getElementsByTag("h4").text());
                board.setBoard_num("");
                board.setWriter(replace(nums.get(i).getElementsByTag("li").get(0).text()));
                board.setDate(replace(nums.get(i).getElementsByTag("li").get(1).text()));
                board.setHits(nums.get(i).getElementsByTag("li").get(2).text());
                repository.save(board);

            }
    }
    private String replace(String text){
            String[] texts= text.split(" ");
            return texts[2];
    }

    /**
     * cnu_news, job_search, offer 제외 모든 게시판
     */

    private void getByTag(Document document, DepthSecond port){
        Elements nums = document.select("td");
        for(int i=0; i<nums.size()-6; i+=6){
            Board board =new Board();
            board.setMenu(port);
            board.setBoard_num(nums.get(i).text()); //번호
            board.setUrl(nums.get(i+1).childNode(0).attr("href")); //url
            board.setName(nums.get(i+1).text()); //제목
            board.setWriter(nums.get(i+2).text()); //적송저
            board.setDate(nums.get(i+3).text()); //작성일
            board.setHits(nums.get(i+4).text()); //조회수
            repository.save(board);
        }
    }

    /**
     * 백마게시판 구인/구직
     */
    private void getByTag2(Document document, DepthSecond port){
        Elements nums = document.select("td");
        for(int i=0; i<nums.size()-6; i+=6){
            Board board =new Board();
            board.setMenu(port);
            board.setBoard_num(nums.get(i).text()); //번호
            board.setUrl(nums.get(i+1).childNode(0).attr("href")); //url
            board.setName(nums.get(i+1).text()); //제목
            board.setWriter(nums.get(i+2).text()); //작성자
            board.setPeriod(nums.get(i+3).text()); //모집기한
            board.setDate(nums.get(i+4).text()); //작성일
            board.setHits(nums.get(i+5).text()); //조회수
            repository.save(board);

        }
    }

    /**
     *국제언어교류본부
     */
    private void getByTag3(Document document, DepthSecond port){
        Elements elements = document.select("tr");
        for(int i=1; i<elements.size(); i++){
            Board board =new Board();
            board.setMenu(port);
            Element element =elements.get(i);
            board.setBoard_num(element.getElementsByClass("b-num-box").text());
            board.setDate(element.getElementsByClass("b-date").text());
            board.setWriter(element.getElementsByClass("b-writer").text());
            board.setHits(element.getElementsByClass("hit").text().split(" ")[1]);
            board.setUrl(port.getPort()+element.getElementsByTag("a").attr("href"));
            board.setName(element.getElementsByTag("a").text());
            repository.save(board);

        }
    }




}
