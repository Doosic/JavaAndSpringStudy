package com.springBootStudy.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Getter
@ConstructorBinding
@RefreshScope // 리프레쉬를 하게되면 다시 빈을 읽어서 등록하는 기능
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
