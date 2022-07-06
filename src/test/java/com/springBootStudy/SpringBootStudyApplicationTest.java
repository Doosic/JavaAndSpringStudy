package com.springBootStudy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    /*
        1.redis 버전설정 ex) redis:latest
        2.withExposedPorts(6379): 각 포트는 명시적으로 노출되어야 한다.
        3.withReuse(true): 컨테이너를 재사용할 수 있도록 한다.

        @DynamicPropertySource - 동적으로 설정 값 매핑하기
        실행된 컨테이너의 host, port 값을 가져와 동적으로 application에 명시된 값을 오버라이딩 한다. 만약
        @DynamicPropertySource 해당 로직을 적확히 작성하지 않으면 redis 서버를 따로 돌리지 않으면
        redis 를 찾을 수 없다.

        1.getHost: 컨테이너 호스트 "localhost를 반환한다"
        2.getMappedPort(6379): 무작위로 매핑된 포트는 컨테이너 시작 후 발생하므로 해당 메소드로 런타임 시 실제 포트를 검색할 수 있다.
    */
    static final String REDIS_IMAGE = "redis:latest";

    private static Logger logger = LoggerFactory.getLogger(SpringBootStudyApplicationTest.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    private static final GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse(REDIS_IMAGE))
            .withExposedPorts(6379)
            .withReuse(true);

    @BeforeAll
    static void setup(){
        redisContainer.followOutput(new Slf4jLogConsumer(logger));
    }

    @DynamicPropertySource
    static void propertiesDynamic(DynamicPropertyRegistry registry){
    //        registry.add("spring.cache.type", () -> "redis");
        registry.add("spring.redis.host", redisContainer::getHost);
        registry.add("spring.redis.port", () -> redisContainer.getMappedPort(6379));
    }

    @Test
    void contextLoads() throws Exception{
        // Given

        // when
        GenericContainer.ExecResult execResult1 = redisContainer.execInContainer("redis-cli", "get", "StudentEntity:cache");
        GenericContainer.ExecResult execResult2 = redisContainer.execInContainer("redis-cli", "get", "StudentEntity:fred");
        GenericContainer.ExecResult execResult3 = redisContainer.execInContainer("redis-cli", "get", "StudentEntity:jack");
        StudentEntity actual1 = objectMapper.readValue(execResult1.getStdout(), StudentEntity.class);
        StudentEntity actual2 = objectMapper.readValue(execResult2.getStdout(), StudentEntity.class);
        StudentEntity actual3 = objectMapper.readValue(execResult3.getStdout(), StudentEntity.class);

        // then
        assertThat(redisContainer.isRunning()).isTrue();
        assertThat(actual1).isEqualTo(StudentEntity.of("cache", 17, StudentEntity.Grade.C));
        assertThat(actual2).isEqualTo(StudentEntity.of("fred", 18, StudentEntity.Grade.A));
        assertThat(actual3).isEqualTo(StudentEntity.of("jack", 15, StudentEntity.Grade.E));


    }

}