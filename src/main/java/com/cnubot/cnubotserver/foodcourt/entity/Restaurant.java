package com.cnubot.cnubotserver.foodcourt.entity;

import com.cnubot.cnubotserver.domain.entity.DepthSecond;
import com.cnubot.cnubotserver.foodcourt.enums.FoodCourt;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "food_court")
    private FoodCourt foodCourt;

    //월 화 수 목 금 토 일
    private String day;
    //조식 중식 석식
    @Lob
    private String breakfast;
    @Lob
    private String lunch;
    @Lob
    private String dinner;

}
