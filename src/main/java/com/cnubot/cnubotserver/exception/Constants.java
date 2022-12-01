package com.cnubot.cnubotserver.exception;

public class Constants {
    public enum ExceptionClass {

        BOARD("Board"), FOOD_COURT("FoodCourt"),BUS("bus");

        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception. ";
        }

    }
}
