package com.cnubot.cnubotserver.foodcourt.controller;

import static com.cnubot.cnubotserver.exception.Constants.ExceptionClass.*;
import static org.springframework.http.HttpStatus.*;

import com.cnubot.cnubotserver.exception.CnuBotException;
import com.cnubot.cnubotserver.exception.Constants.ExceptionClass;
import com.cnubot.cnubotserver.foodcourt.entity.FirstStudentHall;
import com.cnubot.cnubotserver.foodcourt.entity.Menu;
import com.cnubot.cnubotserver.foodcourt.service.FirstHallService;
import com.cnubot.cnubotserver.foodcourt.service.FoodService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FirstHallService firstHallService;
    private final FoodService foodService;

    @ApiOperation(value = "장소에 따른 메뉴 목로들을 반환하는 api"
            , notes = "2학 = SECOND_STUDENT_HALL, 3학 = THIRD_STUDENT_HALL, 4학 = FOURTH_STUDENT_HALL,"
            + " 긱사 = DORMITORY, 생과대 =COLLEGE_OF_LIFE_SCIENCE ")
    @ApiImplicitParam(name = "foodCourt", value = "조회할 식당 이름", required = true, dataType = "String")
    @GetMapping("/")
    public ResponseEntity<List<Menu>> menus(@RequestParam String foodCourt,
                                            @RequestParam String day) {
        List<Menu> menus = foodService.getMenus(foodCourt, day);
        return ResponseEntity.ok().body(menus);
    }

    @PostMapping("/")
    public ResponseEntity<FirstStudentHall> addFood(@RequestBody FoodDto foodDto) throws CnuBotException {
        try {
            FirstStudentHall addedResult = firstHallService.addFood(foodDto);
            return ResponseEntity.ok().body(addedResult);
        } catch (CnuBotException exception) {
            throw exception;
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<FirstStudentHall> deleteFood(@RequestParam String foodName) throws CnuBotException {
        try {
            firstHallService.deleteFood(foodName);
            return ResponseEntity.ok().build();
        } catch (CnuBotException exception) {
            throw exception;
        }
    }


}
