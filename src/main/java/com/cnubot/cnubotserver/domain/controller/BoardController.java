package com.cnubot.cnubotserver.domain.controller;


import com.cnubot.cnubotserver.domain.entity.Board;
import com.cnubot.cnubotserver.domain.service.BoardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  버스와 충남대 홈페이지 소식 게시글 관련 Controller입니다.
 */

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    /**
     *
     * @param menu_name
     * @return Http.status.ok & body(ListOfBoards)
     */

    @ApiOperation(value ="메뉴 이름에 따른 게시글 목록을 반환하는 메소드")
    @ApiImplicitParam(name = "menu_name", value="조회할 게시글 메뉴 이름", required = true,
    dataType = "String")
   @GetMapping("/")
    public ResponseEntity<List<Board>> boards(@RequestParam String menu_name){
        List<Board> boardDtos = boardService.getBoards(menu_name);
        return ResponseEntity.ok().body(boardDtos);
    }

}
