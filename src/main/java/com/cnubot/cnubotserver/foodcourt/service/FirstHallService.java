package com.cnubot.cnubotserver.foodcourt.service;

import com.cnubot.cnubotserver.exception.CnuBotException;
import com.cnubot.cnubotserver.foodcourt.controller.dto.FoodDto;
import com.cnubot.cnubotserver.foodcourt.entity.FirstStudentHall;
import org.springframework.stereotype.Service;

@Service
public interface FirstHallService {

    FirstStudentHall addFood(FoodDto foodDto) throws CnuBotException;

    void deleteFood(String foodName) throws CnuBotException;
}
