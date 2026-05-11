package com.my_geeks.dormitory.common.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_FOUND_END_POINT(HttpStatus.NOT_FOUND, "존재하지 않는 API 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 서버 오류입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public String getCode() {
        return name();
    }
}
