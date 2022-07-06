package com.springBootStudy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
