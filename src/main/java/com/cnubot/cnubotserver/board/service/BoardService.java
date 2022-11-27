package com.cnubot.cnubotserver.board.service;

import com.cnubot.cnubotserver.board.entity.Board;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {

    List<Board> getBoards(String menuName);

    void refreshBoards();


}
