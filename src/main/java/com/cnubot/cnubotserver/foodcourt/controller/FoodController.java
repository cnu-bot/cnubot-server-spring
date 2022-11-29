package com.cnubot.cnubotserver.foodcourt.controller;

import com.cnubot.cnubotserver.exception.CnuBotException;
import com.cnubot.cnubotserver.foodcourt.controller.dto.FoodDto;
import com.cnubot.cnubotserver.foodcourt.entity.FirstStudentHall;
import com.cnubot.cnubotserver.foodcourt.entity.Menu;
import com.cnubot.cnubotserver.foodcourt.service.FirstHallService;
import com.cnubot.cnubotserver.foodcourt.service.FoodService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    @ApiOperation(value = "1학메뉴를 저장  반환하는 api"
            , notes = "type 입력시 RAMYUN_AND_SNACK(\"라면&간식\"),\n"
            + "    WESTERN_FOOD(\"양식\"),\n"
            + "    FUSION_SNACK(\"스낵\"),\n"
            + "    KOREAN_FOOD(\"한식\"),\n"
            + "    JAPANESE_FOOD(\"일식\"),\n"
            + "    CHINESE_FOOD(\"중식\") "
            )
    @ApiImplicitParam(name = "foodDto", value = "저장할 메뉴", required = true, dataType = "Object")
    @PostMapping("/first-student-hall/")
    public ResponseEntity<FirstStudentHall> addFood(@RequestBody FoodDto foodDto) throws CnuBotException {
        try {
            FirstStudentHall addedResult = firstHallService.addFood(foodDto);
            return ResponseEntity.ok().body(addedResult);
        } catch (CnuBotException exception) {
            throw exception;
        }
    }

    @ApiOperation(value = "1학 메뉴를 삭제하는 api"
            , notes = "풀 네임 요구")
    @ApiImplicitParam(name = "foodName", value = "삭제할 메뉴 이름", required = true, dataType = "String")
    @DeleteMapping("/first-student-hall/")
    public ResponseEntity<FirstStudentHall> deleteFood(@RequestParam String foodName) throws CnuBotException {
        try {
            firstHallService.deleteFood(foodName);
            return ResponseEntity.ok().build();
        } catch (CnuBotException exception) {
            throw exception;
        }
    }

    @ApiOperation(value = "푸드코트별 메뉴를 반환하는 api"
            , notes = "input값 형식 RAMYUN_AND_SNACK,WESTERN_FOOD,FUSION_SNACK,KOREAN_FOOD,JAPANESE_FOOD,CHINESE_FOOD")
    @ApiImplicitParam(name = "firstHallType", value = "조회할 푸드코트 이름", required = true, dataType = "String")
    @GetMapping("/first-student-hall/")
    public ResponseEntity<List<FirstStudentHall>> getMenus(@RequestParam String firstHallType) {
        List<FirstStudentHall> menus = firstHallService.getMenus(firstHallType);
        return ResponseEntity.ok().body(menus);
    }


}
