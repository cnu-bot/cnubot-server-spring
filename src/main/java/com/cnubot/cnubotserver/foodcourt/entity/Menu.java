package com.cnubot.cnubotserver.foodcourt.entity;

import com.cnubot.cnubotserver.foodcourt.enums.FoodCourt;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "food_court")
    private FoodCourt foodCourt;

    //월 화 수 목 금 토 일
    private String day;

    private String type;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Food> foods = new ArrayList<>();
}
