package com.cnubot.cnubotserver.foodcourt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.cnubot.cnubotserver.foodcourt.entity.Menu;
import com.cnubot.cnubotserver.foodcourt.enums.FoodCourt;
import com.cnubot.cnubotserver.foodcourt.enums.Week;
import com.cnubot.cnubotserver.foodcourt.repository.MenuRepository;
import com.cnubot.cnubotserver.foodcourt.service.crawling.FoodCrawling;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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
    MenuRepository repository;

    @Test
    void 크롤링_테스트() {
        List<Menu> menus = foodService.getMenus(FoodCourt.DORMITORY.name(), "FRIDAY");

        System.out.println(menus);
        assertThat(menus.size()).isEqualTo(10);

    }


}