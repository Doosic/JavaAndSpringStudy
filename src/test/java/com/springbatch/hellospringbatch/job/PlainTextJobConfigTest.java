
package com.springbatch.hellospringbatch.job;

import com.springbatch.hellospringbatch.BatchTestConfig;
import com.springbatch.hellospringbatch.core.domain.PlainText;
import com.springbatch.hellospringbatch.core.repository.PlainTextRepository;
import com.springbatch.hellospringbatch.core.repository.ResultTextRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.IntStream;

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
@ContextConfiguration(classes = {PlainTextJobConfig.class, BatchTestConfig.class})
public class PlainTextJobConfigTest {

    // JobLauncherTestUtils: 스프링 배치 테스트에 필요한 유틸 기능
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private PlainTextRepository plainTextRepository;

    @Autowired
    private ResultTextRepository resultTextRepository;

    /*
        레포지토리에 읽고 쓰는 작업이 있는데
        테스트를 하며 각 테스트끼리 영향을 받지 않도록
        사용한 레포지토리에 대해서 클린업을 해준다.
        데이터의 생성과 수정됐을때를 대비해 데이터를 삭제해버린다.
    */
    @AfterEach
    public void tearDown(){
        plainTextRepository.deleteAll();
        resultTextRepository.deleteAll();
    }

    // 데이터가 없을때 NullpointException 또는 다른 상황들이 발생했을때에 Job이 멈추지 않고 잘 돌아가는지 확인해줘야한다.
    @Test
    public void success_givenNoPlainText() throws Exception {
        // Given
        // no plainText

        // When
        JobExecution execution = jobLauncherTestUtils.launchJob();

        // Then
        // JobExecution이 잘 COMPLETED 됐는지
        Assertions.assertEquals(execution.getExitStatus(), ExitStatus.COMPLETED);
        // 데이터를 읽고 쓰는 잡이지만 데이터가 없을때를 가정했기에
        // resultTextRepository 가 실행되면 안된다. count == 0 이어야 한다.
        Assertions.assertEquals(resultTextRepository.count(), 0);
    }

    // 데이터가 있을때를 가정
    @Test
    public void success_givenPlainText() throws Exception {
        // Given
        givenPlainTexts(12);

        // When
        JobExecution execution = jobLauncherTestUtils.launchJob();

        // Then
        // JobExecution이 잘 COMPLETED 됐는지
        Assertions.assertEquals(execution.getExitStatus(), ExitStatus.COMPLETED);
        // 데이터를 읽고 쓰는 잡이며, 데이터가 있을때를 가정했기에
        // resultTextRepository가 givenPlainTexts에 있는 데이터 개수만큼 count == 12 이어야 한다.
        Assertions.assertEquals(resultTextRepository.count(), 12);
    }

    private void givenPlainTexts(Integer count) {
        IntStream.range(0 , count)
                .forEach(
                        num -> plainTextRepository.save(new PlainText(null, "text" + num))
                );
    }
}
