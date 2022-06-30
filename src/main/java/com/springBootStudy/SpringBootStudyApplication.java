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
public class SpringBootStudyApplication {

    private final MyProperties myProperties;


    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("[configurationProps] " + myProperties.getAge());
    }



}
