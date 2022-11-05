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

    private final BoardRepository repository;
    private final BoardCrawling boardCrawling;

    /**
     * 메뉴 이름에 속한 게시글들 반환
     */
    @Override
    public List<Board> getBoards(String menu_name) {
        return repository.findAllByMenu(DepthSecond.valueOf(menu_name));
    }

    /**
     * 게시글 갱신
     */
    @Override
    public void refreshBoards() {
        repository.deleteAllInBatch();
        boardCrawling.process();
    }
}
