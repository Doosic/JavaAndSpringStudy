package com.springBootStudy.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties("api.custom.properties")
//@Configuration // 대신 root 에 추가 SpringBootStudyApplication.java 에 @ConfigurationPropertiesScan 을 추가해준다.
public class MyProperties {

    /** 나이 값 */
    private final Integer age;

    public MyProperties(Integer age) {
        this.age = age;
    }

}
