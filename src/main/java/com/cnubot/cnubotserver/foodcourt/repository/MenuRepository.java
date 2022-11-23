package com.cnubot.cnubotserver.foodcourt.repository;

import com.cnubot.cnubotserver.foodcourt.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
}
