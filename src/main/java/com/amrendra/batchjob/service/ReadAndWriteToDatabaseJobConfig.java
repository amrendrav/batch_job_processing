package com.amrendra.batchjob.service;

import com.amrendra.batchjob.readers.InputDataBaseReaderAndWriter;
import com.amrendra.batchjob.pojo.RAndWFromDBPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * @author Amrendra Vimal
 */

@Configuration
public class ReadAndWriteToDatabaseJobConfig {

    public final static Logger logger = LoggerFactory.getLogger(FileReadAndDBWriteBatchJobConfig.class);

    @Value("${chunk.size.dbrr}")
    private int chunkSizeForDBRR;

    @Value("${max.threads}")
    private int maxThreads;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private InputDataBaseReaderAndWriter readerAndWriterConfiguration;

    @Bean
    public Job inputFromDatabaseProcessJob() throws Exception {
        logger.info("Inside input file job processing");

        return jobBuilderFactory.get("inputFromDatabaseProcessJob")
                .incrementer(new RunIdIncrementer())
                .start(dbReadAndWriteProcessStep())
                .build();
    }

    @Bean
    public Step dbReadAndWriteProcessStep() {

        return stepBuilderFactory.get("dbReadAndWriteProcessStep")
                .<RAndWFromDBPOJO, RAndWFromDBPOJO>chunk(chunkSizeForDBRR)
                //.reader(readerAndWriterConfiguration.cursorItemReader())//This Does not works for multithreading
                .reader(readerAndWriterConfiguration.pagingItemReader())
                .writer(readerAndWriterConfiguration.jdbcWriterForDbRWJob())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(maxThreads);
        return taskExecutor;
    }

}
