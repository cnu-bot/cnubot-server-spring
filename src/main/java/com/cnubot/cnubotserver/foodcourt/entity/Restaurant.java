package com.cnubot.cnubotserver.foodcourt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 기숙사, 2학, 3학, 4학, 생과대
    private String place;

    //월 화 수 목 금 토 일
    private String day;
    //조식 중식 석식
    @Lob
    private String breakfast;
    @Lob
    private String lunch;
    @Lob
    private String dinner;

    // 회관, 생과대 (정식 학생or 일품 교직원) , 긱사 main A(칼로리),  main C(칼로리)
   // private String type;



}
