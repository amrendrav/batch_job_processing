package com.bulkprocess.bulkprocess.service;

import com.bulkprocess.bulkprocess.step.CampaignItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bulkprocess.bulkprocess.domain.CampaignTO;
import com.bulkprocess.bulkprocess.step.ChunkReadAndWriteStep;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class BulkProcessing {

	@Value("${chunk.size}")
	private int chunkSize;

	@Value("${max.threads}")
	private int maxThreads;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Autowired
    ChunkReadAndWriteStep campaignStep;


	@Bean
	public Job campaignFileProcessJob() throws Exception{
        System.out.println("Starting Batch Jobs");
		return jobBuilderFactory.get("campaignFileProcessJob")
				.incrementer(new RunIdIncrementer())
				.start(campaignFileStep())
				.build();
	}

	@Bean
    public Step campaignFileStep(){
	    return stepBuilderFactory.get("campaignFileStep")
                .<CampaignTO, CampaignTO>chunk(chunkSize)
                .reader(campaignStep.fileReader())
                .writer(campaignStep.jdbcWriter())
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
