package com.cnubot.cnubotserver.foodcourt.repository;

import com.cnubot.cnubotserver.foodcourt.entity.FirstStudentHall;
import com.cnubot.cnubotserver.foodcourt.enums.FirstHallType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentHallRepository extends JpaRepository<FirstStudentHall, String> {

    boolean existsByFoodName(String foodName);

    void deleteByFoodName(String foodName);

    List<FirstStudentHall> findAllByType(FirstHallType type);
}
