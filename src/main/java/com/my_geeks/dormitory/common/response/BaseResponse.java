package com.my_geeks.dormitory.common.response;

import com.my_geeks.dormitory.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private HttpStatus httpStatus;
    private T data;
    private String code;
    private String errorMessage;

    public static <T> BaseResponse<T> ok(final T data) {
        return new BaseResponse<>(HttpStatus.OK, data, null, null);
    }

    public static <T> BaseResponse<T> created(final T data) {
        return new BaseResponse<>(HttpStatus.CREATED, data, null, null);
    }

    public static <T> BaseResponse<T> noContent() {
        return new BaseResponse<>(HttpStatus.NO_CONTENT, null, null, null);
    }

    public static <T> BaseResponse<T> fail(final CustomException e) {
        return new BaseResponse<>(e.getHttpStatus(), null, e.getCode(), e.getMessage());
    }

    public static <T> BaseResponse<T> validationFail(final MethodArgumentNotValidException e) {
        return new BaseResponse<>(
                HttpStatus.BAD_REQUEST,
                null,
                "INVALID_REQUEST",
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
    }
}
