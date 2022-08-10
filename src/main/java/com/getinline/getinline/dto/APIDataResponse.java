package com.getinline.getinline.dto;

import com.getinline.getinline.constant.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true) // 상속받은 APIErrorResponse 클래스도 동일한지 확인하기 위해서 callSuper 를 사용
public class APIDataResponse<T> extends APIErrorResponse{
    /*
        제네릭을 더 잘 사용해보자 T를 사용하여 어떤 타입이든 들어올 수 있게 해보자
    */

    private final T data;

    protected APIDataResponse(T data) {
        super(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.data = data;
    }

    public static <T> APIDataResponse<T> of(T data){
        return new APIDataResponse<>(data);
    }


}
