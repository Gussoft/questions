package com.gussoft.questions.config;

import com.gussoft.questions.model.dto.MovementItemIn;
import com.gussoft.questions.model.dto.MovementItemOut;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class JobLotMovementsConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    JobRepository jobRepository;

    @Value("${file.input}")
    private String fileInput;

    @Bean
    public JobLauncher jobLotMovementLauncher() throws Exception {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        launcher.afterPropertiesSet();
        return launcher;
    }

    @Bean
    public Job processLotMovementsJob(JobLotMovementsListener listener, Step stepOne) {
        return jobBuilderFactory.get("processLotMovementsJob")
                .listener(listener)
                .flow(stepOne)
                .end()
                .build();
    }

    @Bean
    public Step stepOne() {
        return stepBuilderFactory.get("stepOne")
                .<MovementItemIn, MovementItemOut> chunk(5) // procesa en bloques de 5
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<MovementItemIn> reader() {
        return new FlatFileItemReaderBuilder<MovementItemIn>()
                .name("movementItemReader")
                .resource(new ClassPathResource(fileInput))
                .delimited()
                .names(new String[] {"operationNumber", "amount"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MovementItemIn>() {{
                    setTargetType(MovementItemIn.class);
                }}).build();
    }

    @Bean
    public MovementItemProcessor processor() {
        return new MovementItemProcessor();
    }

    @Bean
    public MovementItemWriter writer() {
        return new MovementItemWriter();
    }
}
