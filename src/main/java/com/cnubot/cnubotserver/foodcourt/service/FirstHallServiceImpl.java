package com.cnubot.cnubotserver.foodcourt.service;

import static com.cnubot.cnubotserver.exception.Constants.ExceptionClass.*;
import static org.springframework.http.HttpStatus.*;

import com.cnubot.cnubotserver.exception.CnuBotException;
import com.cnubot.cnubotserver.foodcourt.controller.dto.FoodDto;
import com.cnubot.cnubotserver.foodcourt.entity.FirstStudentHall;
import com.cnubot.cnubotserver.foodcourt.repository.StudentHallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FirstHallServiceImpl implements FirstHallService {

    private final StudentHallRepository repository;

    @Override
    public FirstStudentHall addFood(FoodDto foodDto) throws CnuBotException {
        FirstStudentHall food = FirstStudentHall.builder()
                .foodName(foodDto.getFoodName())
                .type(foodDto.getType())
                .price(foodDto.getPrice())
                .build();
        if (isExist(foodDto.getFoodName())) {
            throw new CnuBotException(FOOD_COURT, BAD_REQUEST, "중복된 음식입니다");
        }
        return repository.save(food);
    }

    @Override
    public void deleteFood(String foodName) throws CnuBotException {
        if (!isExist(foodName)) {
            throw new CnuBotException(FOOD_COURT, BAD_REQUEST, "존재하지 않는 메뉴입니다.");
        }
        repository.deleteByFoodName(foodName);
    }

    private boolean isExist(String foodName) {
        return repository.existsByFoodName(foodName);
    }
}
