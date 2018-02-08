package com.bulkprocess.bulkprocess.service;

import com.bulkprocess.bulkprocess.pojo.ReadAndWritePojo;
import com.bulkprocess.bulkprocess.readers.InputFIleItemReader;
import com.bulkprocess.bulkprocess.writers.InputFileWriterConfiguration;
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

/**
 * @author Amrendra Vimal
 */

@Configuration
public class FileReadAndDBWriteBatchJobConfig {

	public final static Logger logger = LoggerFactory.getLogger(FileReadAndDBWriteBatchJobConfig.class);

	@Value("${chunk.size}")
	private int chunkSize;

	@Value("${max.threads}")
	private int maxThreads;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
	InputFileWriterConfiguration writerConfiguration;

    @Autowired
    DataSource datasource;

    @Value("${input.file}")
    String inputFile;

	@Bean
	public Job inputFileProcessJob() throws Exception{
		logger.info("Inside input file job processing");

		return jobBuilderFactory.get("inputFileProcessJob")
				.incrementer(new RunIdIncrementer())
				.start(campaignFileStep())
				.build();
	}

	@Bean
    public Step campaignFileStep(){

	    return stepBuilderFactory.get("campaignFileStep")
                .<ReadAndWritePojo, ReadAndWritePojo>chunk(chunkSize)
                .reader(new InputFIleItemReader().fileReader(inputFile))
                .writer(writerConfiguration.jdbcWriter())
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
