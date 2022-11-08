package com.gussoft.questions.batch;

import com.gussoft.questions.model.Movement;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BatchConfig {

    @Value("${file.input}")
    private String fileInput;

    @Bean
    @StepScope
    public FlatFileItemReader<Movement> itemReader() {
        FlatFileItemReader<Movement> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource(fileInput));
        flatFileItemReader.setName("Flat File reader");
        flatFileItemReader.setLineMapper(getLineMapper());
        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }

    private LineMapper<Movement> getLineMapper() {
        DefaultLineMapper<Movement> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("operationNumber", "amount");
        BeanWrapperFieldSetMapper<Movement> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Movement.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
}
