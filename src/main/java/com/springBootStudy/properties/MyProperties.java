package com.springBootStudy.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("api.custom.properties")
//@Configuration // 대신 root 에 추가 SpringBootStudyApplication.java 에 @ConfigurationPropertiesScan 을 추가해준다.
public class MyProperties {

    private Integer age;

    public MyProperties(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}
