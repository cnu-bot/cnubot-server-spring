package com.cnubot.cnubotserver.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DepthSecond menu;

    private String url;

    private String name;

    private String hits;

    @Column(name = "board_num")
    private String boardNum;

    private String date;

    private String writer;

    @Column(name = "pic_url")
    private String picUrl;

    private String period;





}
