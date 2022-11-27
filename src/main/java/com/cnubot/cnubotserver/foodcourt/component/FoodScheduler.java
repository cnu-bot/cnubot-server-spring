package com.cnubot.cnubotserver.foodcourt.component;

import com.cnubot.cnubotserver.foodcourt.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FoodScheduler {

    private final FoodService foodService;

    // 매주 일요일 23시 0분에 초기화
    // 초 분 시간 일 월 요일
    @Scheduled(cron = " 0 0 23 * * 7 ")
    public void refresh(){
        foodService.refreshMenu();
    }
}
