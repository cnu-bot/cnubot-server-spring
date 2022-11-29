package com.cnubot.cnubotserver.foodcourt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.cnubot.cnubotserver.exception.CnuBotException;
import com.cnubot.cnubotserver.foodcourt.controller.dto.FoodDto;
import com.cnubot.cnubotserver.foodcourt.entity.Menu;
import com.cnubot.cnubotserver.foodcourt.enums.FirstHallType;
import com.cnubot.cnubotserver.foodcourt.enums.FoodCourt;
import com.cnubot.cnubotserver.foodcourt.repository.MenuRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class FoodCourtServiceImplTest {
    @Autowired
    FoodService foodService;
    @Autowired
    FirstHallService firstHallService;
    @Autowired
    MenuRepository repository;

    @Test
    void 크롤링_테스트() {
        List<Menu> menus = foodService.getMenus(FoodCourt.DORMITORY.name(), "FRIDAY");

        System.out.println(menus);
        assertThat(menus.size()).isEqualTo(10);

    }

    @Test
    void 일학_음식_추가_중복_테스트() throws CnuBotException {
        FoodDto foodDto = new FoodDto("차돌온면", FirstHallType.CHINESE_FOOD, "6500");
        FoodDto duplicatedDto = new FoodDto("차돌온면", FirstHallType.CHINESE_FOOD, "6500");

        firstHallService.addFood(foodDto);
        assertThatThrownBy(() -> firstHallService.addFood(duplicatedDto))
                .isInstanceOf(CnuBotException.class)
                .hasMessage("FoodCourt Exception. 중복된 음식입니다");

    }

    @Test
    void 일학_음식_제거_테스트() {
        String deleteTargetFoodName = "존재하지 않는 메뉴이름";
        assertThatThrownBy(() -> firstHallService.deleteFood(deleteTargetFoodName))
                .isInstanceOf(CnuBotException.class)
                .hasMessage("FoodCourt Exception. 존재하지 않는 메뉴입니다.");
    }

    @Test
    void 일학_음식_타입으로갖고오기_테스트() throws CnuBotException {
        FoodDto foodDto = new FoodDto("차돌온면", FirstHallType.CHINESE_FOOD, "6500");
        FoodDto foodDto1 = new FoodDto("매운차돌온면", FirstHallType.CHINESE_FOOD, "6500");
        FoodDto foodDto2 = new FoodDto("비빔면", FirstHallType.CHINESE_FOOD, "5500");
        FoodDto foodDto3 = new FoodDto("중국식물냉면", FirstHallType.CHINESE_FOOD, "5500");

        firstHallService.addFood(foodDto);
        firstHallService.addFood(foodDto1);
        firstHallService.addFood(foodDto2);
        firstHallService.addFood(foodDto3);

        assertThat(firstHallService.getMenus("CHINESE_FOOD").size()).isEqualTo(4);
    }


}