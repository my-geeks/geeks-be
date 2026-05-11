package com.my_geeks.dormitory.common.exception;

import com.my_geeks.dormitory.common.exception.errorcode.ErrorCode;
import com.my_geeks.dormitory.common.response.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public BaseResponse<?> handleNoPageFoundException(Exception e) {
        log.error("[Not Found Exception]", e);
        return BaseResponse.fail(new CustomException(ErrorCode.NOT_FOUND_END_POINT));
    }

    @ExceptionHandler(CustomException.class)
    public BaseResponse<?> handleCustomException(CustomException e) {
        log.error("[Custom Exception] {}", e.getMessage());
        return BaseResponse.fail(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> handleValidationException(MethodArgumentNotValidException e) {
        log.warn("[Validation Exception] {}", e.getMessage());
        return BaseResponse.validationFail(e);
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            ConstraintViolationException.class
    })
    public BaseResponse<?> handleBadRequest(Exception e) {
        log.warn("[Bad Request] {}", e.getMessage());
        return BaseResponse.fail(new CustomException(ErrorCode.INVALID_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<?> handleException(Exception e) {
        log.error("[Unhandled Exception]", e);
        return BaseResponse.fail(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
