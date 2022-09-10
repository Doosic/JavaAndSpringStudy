package com.getinline.getinline.error;

import com.getinline.getinline.constant.ErrorCode;
import com.getinline.getinline.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

// VIEW 에 대한 컨트롤 어드바이스
@ControllerAdvice
public class BaseExceptionHandler {

    // 위에는 general Exception 이 터졌을 경우에만
    @ExceptionHandler
    public ModelAndView general(GeneralException e) {
        ErrorCode errorCode = e.getErrorCode();

        return new ModelAndView(
                "error",
                Map.of(
                        "statusCode", errorCode.getHttpStatus().value(),
                        "errorCode", errorCode,
                        "message", errorCode.getMessage()
                ),
                errorCode.getHttpStatus()
        );
    }

    // 일반 예외 처리전략
    @ExceptionHandler
    public ModelAndView exception(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;

        return new ModelAndView(
                "error",
                Map.of(
                        "statusCode", errorCode.getHttpStatus().value(),
                        "errorCode", errorCode,
                        "message", errorCode.getMessage(e)
                ),
                errorCode.getHttpStatus()
        );
    }
}
