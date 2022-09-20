package com.springbatch.hellospringbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
// @EnableBatchProcessing 스프링 배치를 실행하기 위한 설정
@EnableBatchProcessing
// @EnableAutoConfiguration 설정파일이다. 스프링 부트의 meta 파일을 읽어서,
// 미리 정의되어 있는 자바 설정 파일들을 빈으로 등록하는 역할을 수행한다.
// META-INF / spring.factories 에 있는 많은 설정들을 읽어들인다.
@EnableAutoConfiguration
public class BatchTestConfig {

}

