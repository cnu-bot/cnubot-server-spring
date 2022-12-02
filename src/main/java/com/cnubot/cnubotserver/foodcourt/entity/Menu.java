package com.cnubot.cnubotserver.foodcourt.entity;

import com.cnubot.cnubotserver.foodcourt.enums.FoodCourt;
import com.cnubot.cnubotserver.foodcourt.enums.Time;
import com.cnubot.cnubotserver.foodcourt.enums.Week;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "food_court")
    private FoodCourt foodCourt;

    //월 화 수 목 금 토 일
    @Enumerated(EnumType.STRING)
    private Week day;

    @Enumerated(EnumType.STRING)
    private Time time;

    private String type;

    @ElementCollection(targetClass = String.class)
    private List<String> foods = new ArrayList<String>();

    @Override
    public String toString() {
        return "Menu{" +
                "foodCourt=" + foodCourt +
                ", day=" + day +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", foods=" + foods +
                '}';
    }
}
