package com.cnubot.cnubotserver.domain.service;

import com.cnubot.cnubotserver.domain.entity.Board;
import com.cnubot.cnubotserver.domain.entity.DepthSecond;
import com.cnubot.cnubotserver.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardRepository repository;

    BoardCrawling boardCrawling;

    @Override
    public List<Board> getBoards(String menu_name) {

        return repository.findAllByMenu(DepthSecond.valueOf(menu_name));
    }

    @Override
    public void refreshBoards() {
        repository.deleteAll();
        boardCrawling.process();
    }
}
