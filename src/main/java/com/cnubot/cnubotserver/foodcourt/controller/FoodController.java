package com.cnubot.cnubotserver.foodcourt.controller;

import com.cnubot.cnubotserver.foodcourt.entity.Menu;
import com.cnubot.cnubotserver.foodcourt.service.FoodService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/")
    public ResponseEntity<List<Menu>> menus(@RequestParam String foodCourt) {
        List<Menu> menus = foodService.getMenus(foodCourt);
        return ResponseEntity.ok().body(menus);
    }

}
