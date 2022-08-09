package com.getinline.getinline.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true) // 상속받은 APIErrorResponse 클래스도 동일한지 확인하기 위해서 callSuper 를 사용
public class APIDataResponse extends APIErrorResponse{

    private final Object data;

    protected APIDataResponse(Boolean success, Integer errorCode, String message, Object data) {
        super(success, errorCode, message);
        this.data = data;
    }

    public static APIDataResponse of(Boolean success, Integer errorCode, String message, Object data){
        return new APIDataResponse(success, errorCode, message, data);
    }


}
