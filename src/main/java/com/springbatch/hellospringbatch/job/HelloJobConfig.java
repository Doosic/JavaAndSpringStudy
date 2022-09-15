package com.springbatch.hellospringbatch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfig {
    /*
        Job 만들기
        - Job 을 만들때에는 jobBuilderFactory 를 사용한다.

        Step 만들기
        - Step 을 만들때에 StepBuilderFactory 가 필요하다.
        - .incrementer: Job 의 횟수를 일정하게 증가시켜준다.
        - Step은 Bean의 Scope를 지정할 수 있다.
          Step은 애플리케이션이 살아있는 동안에 계속 떠있을 필요가 없기 때문에
          Job이 실행될때만 떠있게 해준다 => @JobScope

        Tasklet 만들기
        - Tasklet 구현체를 설정한다. 구현체 내부에서 단순한 읽기,쓰기,처리 로직을 모두 넣는다.
        - Tasklet 또한 Step이 살아있는 동안에만 실행되면 됨으로
          @StepScope를 사용하여 Step이 살아있는 동안에만 떠있게 해준다.
        - Step은 (contribution, chunkContext) 를 같은 형태로 구성이된다.
          람다표현을 이용하여 Tasklet을 만들고
        - RepeatStatus (반복 상태)를 설정한다. 종료는 => RepeatStatus.FINISHED
    */

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean("helloJob")
    public Job helloJob(Step helloStep){
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer())
                .start(helloStep)
                .build();
    }

    @JobScope
    @Bean("helloStep")
    public Step helloStep(Tasklet tasklet) {
        return stepBuilderFactory.get("helloStep")
                .tasklet(tasklet)
                .build();
    }

    @StepScope
    @Bean
    public Tasklet tasklet(){
        return ((contribution, chunkContext) -> {
            System.out.println("Hello Spring Batch");
            return RepeatStatus.FINISHED;
        });
    }
}
