package com.cnubot.cnubotserver.foodcourt.enums;

public enum FoodCourt {
    SECOND_STUDENT_HALL("https://mobileadmin.cnu.ac.kr/food/index.jsp?searchLang=OCL04.10&searchView=date&searchCafeteria=OCL03.02"),
    THIRD_STUDENT_HALL("https://mobileadmin.cnu.ac.kr/food/index.jsp?searchLang=OCL04.10&searchView=date&searchCafeteria=OCL03.03"),
    FOURTH_STUDENT_HALL("https://mobileadmin.cnu.ac.kr/food/index.jsp?searchLang=OCL04.10&searchView=date&searchCafeteria=OCL03.04"),
    COLLEGE_OF_LIFE_SCIENCE("https://mobileadmin.cnu.ac.kr/food/index.jsp?searchLang=OCL04.10&searchView=date&searchCafeteria=OCL03.05"),
    DORMITORY("https://dorm.cnu.ac.kr/html/kr/sub03/sub03_0304.html");

    private final String port;
    FoodCourt(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }
}
