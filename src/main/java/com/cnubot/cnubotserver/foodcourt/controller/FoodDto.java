package com.cnubot.cnubotserver.foodcourt.controller;

import com.cnubot.cnubotserver.foodcourt.enums.FirstHallType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodDto {

    private final String foodName;
    private final FirstHallType type;
    private final String price;

}
