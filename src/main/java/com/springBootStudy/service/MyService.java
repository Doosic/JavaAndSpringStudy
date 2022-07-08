package com.springBootStudy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope // 리프레쉬를 하게되면 다시 빈을 읽어서 등록하는 기능
@Service
public class MyService {

    private final Integer height;

    public MyService(@Value("${my.height}") Integer height) {
        this.height = height;
    }

    public Integer getHeight(){
        return height;
    }
}
