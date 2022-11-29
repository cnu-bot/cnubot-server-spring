package com.cnubot.cnubotserver.foodcourt.enums;

public enum FirstHallType {
    RAMYUN_AND_SNACK("라면&간식"),
    WESTERN_FOOD("양식"),
    FUSION_SNACK("스낵"),
    KOERAN_FOOD("한식"),
    JAPANESE_FOOD("일식"),
    CHINESE_FOOD("중식");

    String koreaFetch;

    FirstHallType(String koreaFetch) {
        this.koreaFetch = koreaFetch;
    }

    public String getKoreaFetch() {
        return koreaFetch;
    }
}
