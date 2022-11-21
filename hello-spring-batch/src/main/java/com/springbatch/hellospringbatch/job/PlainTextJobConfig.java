package com.springbatch.hellospringbatch.job;

import com.springbatch.hellospringbatch.core.domain.PlainText;
import com.springbatch.hellospringbatch.core.domain.ResultText;
import com.springbatch.hellospringbatch.core.repository.PlainTextRepository;
import com.springbatch.hellospringbatch.core.repository.ResultTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PlainTextJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PlainTextRepository plainTextRepository;
    private final ResultTextRepository resultTextRepository;

    @Bean("plainTextJob")
    public Job plainTextJob(Step plainTextStep){
        return jobBuilderFactory.get("plainTextJob")
                .incrementer(new RunIdIncrementer())
                .start(plainTextStep)
                .build();
    }

    @JobScope
    @Bean("plainTextStep")
    public Step plainTextStep(
            ItemReader plainTextReader,
            ItemProcessor plainTextProcessor,
            ItemWriter plainTextWriter
    ) {
        return stepBuilderFactory.get("plainTextStep")
                .<PlainText, String> chunk(5) // <읽어올 타입, 프로세싱 할 타입> chunk(한 트랜잭션에서 사용할 아이탬 갯수)
                .reader(plainTextReader)// RepositoryItemReader
                .processor(plainTextProcessor) // plainTextProcessor
                .writer(plainTextWriter) // plainTextWriter
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<PlainText> plainTextReader() {
        return new RepositoryItemReaderBuilder<PlainText>()
                .name("plainTextReader") // 이름
                .repository(plainTextRepository) // 사용할 Repository
                .methodName("findBy") // 사용될 method
                .pageSize(5) // 읽어들일 페이지 개수(commit interval: 트랜잭션 커밋이 호출되기 전 처리되어야 하는 아이탬들의 갯수)
                .arguments(List.of()) // 인수로 넘어갈 데이터
                .sorts(Collections.singletonMap("id", Sort.Direction.DESC)) // 정렬
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<PlainText, String> plainTextProcessor(){
        return item -> "processed " + item.getText();
    }

    @StepScope
    @Bean
    public ItemWriter<String> plainTextWriter(){
        return items -> {
            items.forEach(item -> resultTextRepository.save(new ResultText(null, item)));
            System.out.println("=== chunk is finished ===");
        };
    }

}
