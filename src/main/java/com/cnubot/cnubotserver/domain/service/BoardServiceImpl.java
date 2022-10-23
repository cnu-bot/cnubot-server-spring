package com.cnubot.cnubotserver.domain.service;

import com.cnubot.cnubotserver.domain.entity.Board;
import com.cnubot.cnubotserver.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    BoardRepository repository;
    BoardCrawling boardCrawling;

    @Override
    public List<Board> getBoards(String menu_name) {
        return repository.findAllByDepth_second(menu_name);
    }

    @Override
    public void refreshBoards() {
        repository.deleteAllInBatch();
        boardCrawling.process();
    }
}
