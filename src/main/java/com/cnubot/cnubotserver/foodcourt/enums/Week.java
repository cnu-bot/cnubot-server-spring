package com.cnubot.cnubotserver.foodcourt.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Week {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private String day;

    public String getDay() {
        return day;
    }

    Week(String day) {
        this.day = day;
    }


}
