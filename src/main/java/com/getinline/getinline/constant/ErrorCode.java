package com.getinline.getinline.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 정상적인 케이스
    OK(0, ErrorCategory.NORMAL, "Ok"),

    // 클라이언트에게 문제가 있었을 경우
    BAD_REQUEST(10000, ErrorCategory.CLIENT_SIDE, "Bad request"),
    SPRING_BAD_REQUEST(10001, ErrorCategory.CLIENT_SIDE, "Spring-detected bad request"),
    VALIDATION_ERROR(10002, ErrorCategory.CLIENT_SIDE, "Validation error"),

    // 서버에 문제가 있었을 경우
    INTERNAL_ERROR(20000, ErrorCategory.SERVER_SIDE, "Internal error"),
    SPRING_INTERNAL_ERROR(20001, ErrorCategory.SERVER_SIDE, "Spring-detected internal error"),
    DATA_ACCESS_ERROR(20002, ErrorCategory.SERVER_SIDE, "Data access error")
    ;

    private final Integer code;
    private final ErrorCategory errorCategory;
    private final String message;

    // 익셉션을 받을 경우
    public String getMessage(Exception e){
        return getMessage(this.getMessage() + " - " +e.getMessage());
    }

    // message 받아서 비어있지 않다면 사용하고 아니라면 만들어뒀던것을 사용(사용자 정의 메세지)
    public String getMessage(String message){
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank)) // => isBlank 공백이 아니라면
                .orElse(getMessage());
    }

    public boolean isClientSideError() {
        return this.getErrorCategory() == ErrorCategory.CLIENT_SIDE;
    }

    public boolean isServerSideError() {
        return this.getErrorCategory() == ErrorCategory.SERVER_SIDE;
    }

    // lombok 을 사용하지 않은 이유는 간단하게만 보여주기 위해서 ToString 을 직접 구현함
    @Override
    public String toString(){
        return String.format("%s (%d)", name(), this.getCode());
    }


    public enum ErrorCategory{
        NORMAL, CLIENT_SIDE, SERVER_SIDE
    }

}
