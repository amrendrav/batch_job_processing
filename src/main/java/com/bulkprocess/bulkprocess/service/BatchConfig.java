package com.bulkprocess.bulkprocess.service;

import com.bulkprocess.bulkprocess.listeners.BatchJobCompletionListener;
import com.bulkprocess.bulkprocess.step.BatchItemProcessor;
import com.bulkprocess.bulkprocess.step.BatchItemReader;
import com.bulkprocess.bulkprocess.step.BatchItemWriter;
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
import org.springframework.stereotype.Service;

@Configuration
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	ApplicationContext applicationContext;

	// This job runs in every 5 seconds
	@Scheduled(cron = "${my.cron}")
	public void printMessage() {
		System.out.println(applicationContext.getBean("processJob"));
		try {
			System.out.println("I am starting from Schedular");
			JobParameters jobParameters = new JobParametersBuilder().addLong(
					"time", System.currentTimeMillis()).toJobParameters();
			jobLauncher.run((Job)applicationContext.getBean("campaignFileProcessJob"), jobParameters);
			System.out.println("I am ending Scheduled Job");
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
		return stepBuilderFactory.get("orderStep1").<String, String> chunk(1)
				.reader(new BatchItemReader())
				.processor(new BatchItemProcessor())
				.writer(new BatchItemWriter()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new BatchJobCompletionListener();
	}

	@Bean
	public ResourcelessTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}

}