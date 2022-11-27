package com.cnubot.cnubotserver.foodcourt.service;

import com.cnubot.cnubotserver.foodcourt.entity.Menu;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface FoodService {

    void refreshMenu();

    List<Menu> getMenus(String foodCourt);

}
