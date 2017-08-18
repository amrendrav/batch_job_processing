package com.bulkprocess.bulkprocess.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bulkprocess.bulkprocess.domain.CampaignTO;
import com.bulkprocess.bulkprocess.step.ChunkReadAndWriteStep;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class BulkProcessing {

	@Value("${chunk-size}")
	private int chunkSize;

	@Value("${max-threads}")
	private int maxThreads;
	
	@Bean
	Job bulkProcessJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ChunkReadAndWriteStep campaignStep) throws Exception{
		
		Step step = stepBuilderFactory.get("file->db")
				.<CampaignTO, CampaignTO>chunk(chunkSize)
				.reader(campaignStep.fileReader())
				.writer(campaignStep.jdbcWriter())
				.taskExecutor(taskExecutor())
				.build();

		return jobBuilderFactory.get("bulkprocess2")
				.start(step)
				.build();
	}


	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
		taskExecutor.setConcurrencyLimit(maxThreads);
		return taskExecutor;
	}

}
