package com.cnubot.cnubotserver.domain.repository;

import com.cnubot.cnubotserver.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, String> {
    List<Board> findAllByDepth_second(@Param("menu_name") String menu_name);
    //List<Board> findAllByDepth_secondOrderByBoard_numDesc(@Param("menu_name") String menu_name);

}
