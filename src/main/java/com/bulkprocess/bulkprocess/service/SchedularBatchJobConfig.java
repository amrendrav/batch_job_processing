package com.bulkprocess.bulkprocess.service;

import com.bulkprocess.bulkprocess.listeners.SchedularBatchJobCompletionListener;
import com.bulkprocess.bulkprocess.processors.ScheduledBatchJobItemProcessor;
import com.bulkprocess.bulkprocess.readers.ScheduledBatchJobItemReader;
import com.bulkprocess.bulkprocess.writers.ScheduledBatchJobItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * @author Amrendra Vimal
 */

@Configuration
public class SchedularBatchJobConfig {

    public final static Logger logger = LoggerFactory.getLogger(SchedularBatchJobConfig.class);

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;

    // This job runs in every minute
    @Scheduled(cron = "${my.cron}")
    public void printMessage() {
        try {
            logger.info("I am starting from Schedular");

            JobParameters jobParameters = new JobParametersBuilder().addLong(
                    "time", System.currentTimeMillis()).toJobParameters();
            jobLauncher.run((Job)applicationContext.getBean("processJob"), jobParameters);

            logger.info("I am exiting Scheduled Job");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(orderStep1()).end().build();
    }

    /**
     * To create a step, reader, processor and writer has been passed serially
     *
     * @return
     */
    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1").<String, String>chunk(1)
                .reader(new ScheduledBatchJobItemReader())
                .processor(new ScheduledBatchJobItemProcessor())
                .writer(new ScheduledBatchJobItemWriter()).build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new SchedularBatchJobCompletionListener();
    }

    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

}