package com.springBootStudy.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
//@ConfigurationProperties("api.custom.properties")
@RequiredArgsConstructor
@ConfigurationProperties("my")
//@Configuration // 대신 root 에 추가 SpringBootStudyApplication.java 에 @ConfigurationPropertiesScan 을 추가해준다.
public class MyProperties {

    /**
     * 나이 값
     */
    private final Integer height;


}
