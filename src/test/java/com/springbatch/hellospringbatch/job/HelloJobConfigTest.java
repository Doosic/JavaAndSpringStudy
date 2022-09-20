package com.springbatch.hellospringbatch.job;

import com.springbatch.hellospringbatch.BatchTestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @SpringBatchTest: 사용시 Batch Test 에서 사용할 수 있는 유용한 스프링 빈을 등록해준다.
@SpringBatchTest
// @SpringBootTest: 실제 애플리케이션을 자신의 로컬 위에 올려서 포트 주소가 Listening 되고, 실제 Database 와 커넥션이 붙는 상태에서 진행되는 Live 테스트 방법
// @WebMvcTest: Controller 레이어만을 테스트하기에 적합한 테스트 어노테이션으로 전체 애플리케이션 실행이 아닌 Controller 만들 로드하여 테스트가능(소규모 테스트)
@SpringBootTest
// @ExtendWith: 단위 테스트간에 공통적으로 사용할 기능을 구현하여 @ExtendWith 를 통하여 적용할 수 있는 기능을 제공한다.
// SpringExtension 은 Spring TestContext 프레임워크를 JUnit 5의 Jupiter 프로그래밍 모델에 통합합니다.
@ExtendWith(SpringExtension.class)
// application.yml 에 설정한 test 설정을 여기에 입혀준다. h2database 사용
@ActiveProfiles("test")
// 스프링 컨텍스트 설정파일을 읽어들인다. 여기서는 HelloJobConfig, BatchTestConfig 를 읽어온다.
@ContextConfiguration(classes = {HelloJobConfig.class, BatchTestConfig.class})
public class HelloJobConfigTest {

    // JobLauncherTestUtils: 스프링 배치 테스트에 필요한 유틸 기능
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Test
    public void success() throws Exception {
        // When
        JobExecution execution = jobLauncherTestUtils.launchJob();

        // Then
        assertEquals(execution.getExitStatus(), ExitStatus.COMPLETED);
    }

}
