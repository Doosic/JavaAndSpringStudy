package com.springBootStudy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class SpringBootStudyApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(SpringBootStudyApplicationTest.class);

    @Container
    private static final GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse("redis:latest"));

    @BeforeAll
    static void setup(){
        redisContainer.followOutput(new Slf4jLogConsumer(logger));
    }

    @DynamicPropertySource
    static void propertiesDynamic(DynamicPropertyRegistry registry){
//        registry.add("spring.cache.type", () -> "redis");
        registry.add("spring:redis:port", () -> redisContainer.getMappedPort(6379));
    }

    @Test
    void contextLoads() throws Exception{
        // Given

        // when
//        GenericContainer.ExecResult execResult1 = redisContainer.execInContainer("redis-cli", "get", "student::cache");
//        GenericContainer.ExecResult execResult2 = redisContainer.execInContainer("redis-cli", "get", "student::fred");
//        GenericContainer.ExecResult execResult3 = redisContainer.execInContainer("redis-cli", "get", "student::jack");

        // then
        assertThat(redisContainer.isRunning()).isTrue();


    }

}