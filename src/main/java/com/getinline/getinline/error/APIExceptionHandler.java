package com.getinline.getinline.error;

import com.getinline.getinline.constant.ErrorCode;
import com.getinline.getinline.dto.APIErrorResponse;
import com.getinline.getinline.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

// API 에 대한 RestController 어드바이스
// 참고로 ResponseEntity 를 쓴다면 굳이 ResponseBody 로 응답하지 않아도된다.
// API 한정이기에 RestController 를 사용하는 곳에만 적용된다.
@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler {

    // 위에는 general Exception 이 터졌을 경우에만
    @ExceptionHandler
    public ResponseEntity<APIErrorResponse> general(GeneralException e){
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ?
                HttpStatus.BAD_REQUEST :
                HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false, errorCode, errorCode.getMessage(e)
                ));
    }

    // 일반 예외 처리전략
    @ExceptionHandler
    public ResponseEntity<APIErrorResponse> exception(Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false, errorCode, errorCode.getMessage(e)
                ));
    }
}
