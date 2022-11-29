package com.cnubot.cnubotserver.foodcourt.service;

import com.cnubot.cnubotserver.foodcourt.entity.Menu;
import com.cnubot.cnubotserver.foodcourt.enums.FoodCourt;
import com.cnubot.cnubotserver.foodcourt.enums.Week;
import com.cnubot.cnubotserver.foodcourt.repository.MenuRepository;
import com.cnubot.cnubotserver.foodcourt.service.crawling.FoodCrawling;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final MenuRepository menuRepository;
    private final FoodCrawling foodCrawling;

    @Override
    public void refreshMenu() {
        menuRepository.deleteAllInBatch();
        foodCrawling.process();
    }

    @Override
    public List<Menu> getMenus(String foodCourt, String day) {
        return menuRepository.findAllByFoodCourtAndDay(FoodCourt.valueOf(foodCourt), Week.valueOf(day));

    }
}
