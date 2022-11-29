package com.cnubot.cnubotserver.foodcourt.repository;

import com.cnubot.cnubotserver.foodcourt.entity.FirstStudentHall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentHallRepository extends JpaRepository<FirstStudentHall, String> {

    boolean existsByFoodName(String foodName);

    void deleteByFoodName(String foodName);
}
