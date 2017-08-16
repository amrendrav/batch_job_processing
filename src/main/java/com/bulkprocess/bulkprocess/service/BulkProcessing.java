package com.bulkprocess.bulkprocess.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bulkprocess.bulkprocess.domain.MyEntity;
import com.bulkprocess.bulkprocess.step.ChunkReadAndWriteStep;

@Configuration
@EnableBatchProcessing
public class BulkProcessing {
	
	@Bean
	Job bulkProcessJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ChunkReadAndWriteStep campaignStep) throws Exception{
		
		Step step = stepBuilderFactory.get("file-db")
				.<MyEntity, MyEntity>chunk(100)
				.reader(campaignStep.fileReader(null))
				.writer(writer)
	}
	

}
