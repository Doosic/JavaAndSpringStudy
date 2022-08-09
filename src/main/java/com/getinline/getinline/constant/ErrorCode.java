package com.getinline.getinline.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(0, ErrorCategory.NORMAL, "Ok"),

    BAD_REQUEST(10000, ErrorCategory.CLIENT_SIDE, "bad request"),
    SPRING_BAD_REQUEST(10001, ErrorCategory.CLIENT_SIDE, "Spring-detected bad request"),

    INTERNAL_ERROR(20000, ErrorCategory.SERVER_SIDE, "internal error"),
    SPRING_INTERNAL_ERROR(20001, ErrorCategory.SERVER_SIDE, "Spring-detected internal error")
    ;

    private final Integer code;
    private final ErrorCategory errorCategory;
    private final String message;

    public String getMessage(Exception e){
        return getMessage(e.getMessage());
    }

    // message 받아서 비어있지 않다면 사용하고 아니라면 만들어뒀던것을 사용
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

    @Override
    public String toString(){
        return String.format("%s (%d)", name(), this.getCode());
    }


    public enum ErrorCategory{
        NORMAL, CLIENT_SIDE, SERVER_SIDE
    }

}
