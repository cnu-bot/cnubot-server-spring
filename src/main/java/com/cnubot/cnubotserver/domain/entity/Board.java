package com.cnubot.cnubotserver.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DepthSecond depth_second;

    //해당게시글 url 저장
    private String url;
    //해당게시글 이름
    private String name;
    //해당게시글 조회수
    private String hits;
    //해당게시글 Number
    private String board_num;
    //해당게시글 작성날짜
    private String date;
    //해강게시글 작성자
    private String writer;
    //해당게시글 thumbnail CNU news에만 존재
    private String pic_url;
    //채용 관련 3개의 메뉴에만 해당 모집기한
    private String period;





}
