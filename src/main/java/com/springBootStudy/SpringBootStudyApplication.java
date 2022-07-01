package com.springBootStudy;

import com.springBootStudy.properties.MyProperties;
import com.springBootStudy.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@ConfigurationPropertiesScan
@SpringBootApplication
public class SpringBootStudyApplication {

    private final MyProperties myProperties;
    private final StudentService studentService;


    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

//    @PostConstruct
    @EventListener(ApplicationReadyEvent.class) // 캐시는 모든 Bean 들이 생긴 뒤 활성화 된다.
    public void init() {
//        System.out.println("[configurationProps] " + myProperties.getAge());
        studentService.printStudent("jack");
        studentService.printStudent("jack");
        studentService.printStudent("jack");
        studentService.printStudent("fred");
        studentService.printStudent("cache");
        studentService.printStudent("cache");
    }




}
