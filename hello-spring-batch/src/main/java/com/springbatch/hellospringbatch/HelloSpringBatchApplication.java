package com.springbatch.hellospringbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    @EnableBatchProcessing
    Enable 이 붙는 어노테이션이 많아 무슨뜻인지 궁금했었는데, 명쾌한 단어였다.
    Enable = 할 수 있게 하다!
    EnableBatchProcessing = 배치 작업을 처리할 수 있게 하다.(일괄 처리 활성화)
*/
@EnableBatchProcessing
@SpringBootApplication
public class HelloSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBatchApplication.class, args);
    }

}
