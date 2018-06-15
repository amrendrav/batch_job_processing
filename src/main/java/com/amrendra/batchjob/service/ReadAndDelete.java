package com.amrendra.batchjob.service;

import com.amrendra.batchjob.pojo.DeleteTest;
import com.amrendra.batchjob.pojo.ReadAndWritePojo;
import com.amrendra.batchjob.readers.DeleteRowReader;
import com.amrendra.batchjob.readers.InputFIleItemReader;
import com.amrendra.batchjob.writers.BookJdbcItemWriter;
import com.amrendra.batchjob.writers.InputFileWriterConfiguration;
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

import javax.sql.DataSource;

@Configuration
public class ReadAndDelete {


       /* public final static Logger logger = LoggerFactory.getLogger(com.amrendra.batchjob.service.ReadAndDelete.class);

        @Value("${chunk.size}")
        private int chunkSize;

        @Value("${max.threads}")
        private int maxThreads;

        @Autowired
        private JobBuilderFactory jobBuilderFactory;

        @Autowired
        private StepBuilderFactory stepBuilderFactory;

        @Autowired
        private BookJdbcItemWriter writerConfiguration;

        @Autowired
        private DataSource datasource;

        @Value("${input.file}")
        String inputFile;

        @Bean
        public Job inputDeleteDataJob() throws Exception{
            logger.info("Inside inputDeleteDataJob file job processing");

            return jobBuilderFactory.get("inputDeleteDataJob")
                    .incrementer(new RunIdIncrementer())
                    .start(deleteFileStep())
                    .build();
        }

        @Bean
        public Step deleteFileStep(){

logger.info("Something");

            return stepBuilderFactory.get("deleteFileStep")
                    .<Integer, DeleteTest>chunk(chunkSize)
                    .reader(new DeleteRowReader())
                    .writer(writerConfiguration.jdbcDelete())
                    .taskExecutor(taskExecutor())
                    .build();
        }


        @Bean
        public TaskExecutor taskExecutor() {
            SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
            taskExecutor.setConcurrencyLimit(maxThreads);
            return taskExecutor;
        }*/

    }




