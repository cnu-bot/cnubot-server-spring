package com.cnubot.cnubotserver.domain.repository;

import com.cnubot.cnubotserver.domain.entity.Board;
import com.cnubot.cnubotserver.domain.entity.DepthSecond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    List<Board> findAllByMenu(@Param("menu") DepthSecond menu);
    //List<Board> findAllByDepth_secondOrderByBoard_numDesc(@Param("menu_name") String menu_name);

}
