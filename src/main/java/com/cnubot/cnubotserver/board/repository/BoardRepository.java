package com.cnubot.cnubotserver.board.repository;

import com.cnubot.cnubotserver.board.entity.Board;
import com.cnubot.cnubotserver.board.entity.DepthSecond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {

    List<Board> findAllByMenu(@Param("menu") DepthSecond menu);

}
