package com.cnubot.cnubotserver.exception;

import org.springframework.http.HttpStatus;

public class CnuBotException extends Exception {
    // 직렬화를 위한 일련번호
    private static final long serialVersionUID = 4663380430591151694L;

    private Constants.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public CnuBotException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus,
                           String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    // 에러에 대한 상수정의되어있음 100번대부터 400번대까지
    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
