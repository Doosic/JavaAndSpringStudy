package com.springbatch.hellospringbatch.job;

import com.springbatch.hellospringbatch.job.validator.LocalDateParameterValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@AllArgsConstructor
@Slf4j
public class AdvancedJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
     public Job advancedJob(
             Step advancedStep,
             JobExecutionListener jobExecutionListener
    ) {
         return jobBuilderFactory.get("advancedJob")
                 .incrementer(new RunIdIncrementer())
                 .validator(new LocalDateParameterValidator("targetDate")) // validator로 넘어와서는 안될값들을 걸러준다.
                 .listener(jobExecutionListener)
                 .start(advancedStep)
                 .build();
     }

     @JobScope
     @Bean
     public Step advancedStep(Tasklet advancedTasklet) {
         return stepBuilderFactory.get("advancedStep")
                 .tasklet(advancedTasklet)
                 .build();
     }

     @JobScope
     @Bean
     public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("[jobExecutionListener#beforeJob] jobExecution is " + jobExecution.getStatus());
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.FAILED) {
                    // job에서 에러가 발생한다면 별도로 알림을 받고싶다(이메일,카톡 등...) 라는 설정을 해볼 수 있다.
                    log.info("[jobExecutionListener#afterJob] jobExecution is FALILED!!! RECOVER ASAP");
                }
            }
        };
     }

     @StepScope
     @Bean
     public Tasklet advancedTasklet(@Value("#{jobParameters['targetDate']}") String targetDate) {
         return ((contribution, chunkContext) -> {
             log.info("[advancedTasklet] JobParameter - targetDate = " +  targetDate);
             // targetDate로 올바른 값이 넘어오지 않을시 DateTimeParseException 오류 발생
             // 그렇기에 불가능한 값이 넘어오면 Job이 실행전 미리 넘어와서는 안될값을 JobParamater를 통해 거르는게 좋다.
             // + 앞서 Step과 Processor에서 작업이 이루어진후 마지막에서 문제가 생기기보다는 미리 막는것이 중요하다.
             LocalDate executionDate = LocalDate.parse(targetDate);
             // executionDate -> 로직 수행
             log.info("[advancedTasklet] executed advancedTasklet");
             // afterJob에서 FAILED 걸리는걸 확인하기 위해 일부러 RuntimeException 발동
//             throw new RuntimeException("ERROR!!!!");
//             return RepeatStatus.FINISHED;
         });
     }
}
