package com.getinline.getinline.error;

import com.getinline.getinline.constant.ErrorCode;
import com.getinline.getinline.dto.APIErrorResponse;
import com.getinline.getinline.exception.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;


// API 에 대한 RestController 어드바이스
// 참고로 ResponseEntity 를 쓴다면 굳이 ResponseBody 로 응답하지 않아도된다.
// API 한정이기에 RestController 를 사용하는 곳에만 적용된다.
@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request){
        return getInternalResponseEntity(
                e, ErrorCode.VALIDATION_ERROR, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST ,request
        );
    }


    // 위에는 general Exception 이 터졌을 경우에만, 우리가 예상할수 있는 애러
    @ExceptionHandler
    public ResponseEntity<Object> general(GeneralException e, WebRequest request){
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ?
                HttpStatus.BAD_REQUEST :
                HttpStatus.INTERNAL_SERVER_ERROR;

        return getInternalResponseEntity(
                e, errorCode, HttpHeaders.EMPTY, status ,request
        );
    }

    // 일반 예외 처리전략. 예상하지 못하기에 INTERNAL_ERROR 를 던져준다.
    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request){
        return getInternalResponseEntity(
                e, ErrorCode.INTERNAL_ERROR, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR ,request
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Spring 에서 발생한 Error
        ErrorCode errorCode = status.is4xxClientError() ?
                ErrorCode.SPRING_BAD_REQUEST :
                ErrorCode.INTERNAL_ERROR;
        return getInternalResponseEntity(
                ex, errorCode, headers, status ,request
        );
    }

    private ResponseEntity<Object> getInternalResponseEntity(Exception e, ErrorCode errorCode, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(false, errorCode.getCode(), errorCode.getMessage(e)),
                HttpHeaders.EMPTY,
                status,
                request
        );
    }
}
