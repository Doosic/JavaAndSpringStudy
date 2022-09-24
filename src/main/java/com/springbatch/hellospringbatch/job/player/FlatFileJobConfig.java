package com.springbatch.hellospringbatch.job.player;


import com.springbatch.hellospringbatch.core.service.PlayerSalaryService;
import com.springbatch.hellospringbatch.dto.PlayerDto;
import com.springbatch.hellospringbatch.dto.PlayerSalaryDto;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.FileSystem;
import java.util.List;

@Configuration
@AllArgsConstructor
public class FlatFileJobConfig {

    // JobRepository 를 자동으로 설정해준다.
    private final JobBuilderFactory jobBuilderFactory;
    // JobRepository, TransactionManager 를 자동으로 설정해준다.
    private final StepBuilderFactory stepBuilderFactory;

    /*
        Job 트랜잭션 실행
        1.Step
        2.Processor
        3.Writer
        4.Job 트랜잭션 커밋
    */
    @Bean
    public Job flatFileJob(Step flatFileStep) {
        return jobBuilderFactory.get("flatFileJob")
                // .incrementer: job 의 횟수를 일정하게 증가시켜준다.
                .incrementer(new RunIdIncrementer())
                .start(flatFileStep)
                .build();
    }

    @JobScope
    @Bean
    public Step flatFileStep(
            FlatFileItemReader<PlayerDto> playerFileItemReader,
            ItemProcessorAdapter<PlayerDto, PlayerSalaryDto> playerSalaryItemProcessorAdapter
    ) {
        return stepBuilderFactory.get("flatFileStep")
                // PlayerDto 를 PlayerSalaryDto 로 변경한다.
                .<PlayerDto, PlayerSalaryDto>chunk(5)
                .reader(playerFileItemReader)
                .processor(playerSalaryItemProcessorAdapter)
                .writer(new ItemWriter<>() {
                    @Override
                    public void write(List<? extends PlayerSalaryDto> items) throws Exception {
                        items.forEach(System.out::println);
                    }
                })
                .build();
    }

    /*
        Adapter 을 사용하면 좋은점(조금더 간단해진다. 가독성 향상)
        - ItemProcessor을 별도로 만들어서 호출하는것과 같은 동작을 한다.
        - TargetObject로 호출할 서비스를 지정하고
        - TargetMethod를 통해 사용할 메서드를 지정한다.
     */
    @StepScope
    @Bean
    public ItemProcessorAdapter<PlayerDto,PlayerSalaryDto> playerSalaryItemProcessorAdapter(
            PlayerSalaryService playerSalaryService
    ) {
        ItemProcessorAdapter<PlayerDto,PlayerSalaryDto> adapter = new ItemProcessorAdapter<>();
        adapter.setTargetObject(playerSalaryService);
        adapter.setTargetMethod("calcSalary");
        return adapter;
    }

    @StepScope
    @Bean
    public ItemProcessor<PlayerDto, PlayerSalaryDto> playerSalaryItemProcessor(
            PlayerSalaryService playerSalaryService
    ){
        return new ItemProcessor<PlayerDto, PlayerSalaryDto>() {
            @Override
            public PlayerSalaryDto process(PlayerDto item) throws Exception {
                return playerSalaryService.calcSalary(item);
            }
        };
    }


    /*
        FlatFileItemReader 를 통해 File에 있는 데이터를 읽어들일 수 있다.
        직접 반복문을 만들어서 읽어들일수도있지만 framework 에서 제공하는 기능을
        잘 사용하는게 더 효율적이고, 대용량 처리에 적합하다.
    */
    @StepScope
    @Bean
    public FlatFileItemReader<PlayerDto> playerDtoFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<PlayerDto>()
                .name("playFileItemReader")
                // DelimitedLineTokenizer: 읽을때 구분하는 기준은 컴마(',')로 나뉜다.
                .lineTokenizer(new DelimitedLineTokenizer())
                // 한 줄을 건너띄고 읽는다. 보통 첫줄은 헤더에 해당한다.
                .linesToSkip(1)
                /*
                    line 에 있는 데이터를 어떻게 객체로 매핑할지 방법을 정해야 한다.
                    구현시 구현해야할 인터페이스가 무엇인지는 fieldSetMapper() 를 들어가보면 안다.
                    fieldSetMapper(FieldSetMapper<T> mapper): FieldSetMapper 인터페이스에 구현체를 구현해야한다.
                    결론: 어떠한 타입을 인수로 받는지 확인후, 해당 인터페이스에 구현체를 만들어주면 된다.
                 */
                .fieldSetMapper(new PlayerFieldSetMapper())
                .resource(new FileSystemResource("player-list.txt"))
                .build();

    }
}
