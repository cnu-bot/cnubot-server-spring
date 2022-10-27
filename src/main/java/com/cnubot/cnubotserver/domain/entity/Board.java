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
    private DepthSecond menu;

    private String url;

    private String name;

    private String hits;

    private String board_num;

    private String date;

    private String writer;

    private String pic_url;

    private String period;





}
