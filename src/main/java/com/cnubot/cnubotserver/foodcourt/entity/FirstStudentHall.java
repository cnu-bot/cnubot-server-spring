package com.cnubot.cnubotserver.foodcourt.entity;

import com.cnubot.cnubotserver.foodcourt.enums.FirstHallType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FirstStudentHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    private String foodName;

    @Enumerated(EnumType.STRING)
    private FirstHallType type;

    private String price;
}
