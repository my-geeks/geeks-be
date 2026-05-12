package com.my_geeks.dormitory.common.swagger;

import com.my_geeks.dormitory.common.exception.errorcode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {

    private String httpStatus;
    private Object data;
    private String code;
    private String errorMessage;

    static ErrorDto from(ErrorCode errorCode) {
        return new ErrorDto(
                errorCode.getHttpStatus().name(),
                null,
                errorCode.getCode(),
                errorCode.getMessage()
        );
    }
}
