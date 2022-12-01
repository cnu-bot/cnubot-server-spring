package com.cnubot.cnubotserver.foodcourt.controller.dto;

import com.cnubot.cnubotserver.foodcourt.enums.FirstHallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

    private String foodName;
    private FirstHallType type;
    private String price;
    
}
