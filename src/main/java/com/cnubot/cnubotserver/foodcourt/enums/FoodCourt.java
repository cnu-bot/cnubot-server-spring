package com.cnubot.cnubotserver.foodcourt.enums;

public enum FoodCourt {
    STUDENT_HALL("https://mobileadmin.cnu.ac.kr/food/index.jsp"),
    DORMITORY("https://dorm.cnu.ac.kr/html/kr/sub03/sub03_0304.html");

    private final String port;
    FoodCourt(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }
}
