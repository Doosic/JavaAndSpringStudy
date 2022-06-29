package com.springBootStudy;

import com.springBootStudy.properties.MyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
@RequiredArgsConstructor
@ConfigurationPropertiesScan
@SpringBootApplication(
        exclude = {WebMvcAutoConfiguration.class}
)
public class SpringBootStudyApplication { // 강의 7분 까지 들었음

    //    @Value("${api.custom.properties.age}")
    private final Integer age;
    private final Environment environment;
    private final ApplicationContext applicationContext;
    private final MyProperties myProperties;


    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("[@Value] " + age);
        System.out.println("[Environment] " + environment.getProperty("api.custom.properties.age"));
        System.out.println("[ApplicationContext] " + applicationContext.getEnvironment().getProperty("api.custom.properties.age"));
        System.out.println("[configurationProps] " + myProperties.getAge());
    }



}
