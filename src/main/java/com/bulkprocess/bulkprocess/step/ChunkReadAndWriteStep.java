package com.bulkprocess.bulkprocess.step;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.bulkprocess.bulkprocess.domain.MyEntity;

@Configuration
public class ChunkReadAndWriteStep {
	@Bean
	public FlatFileItemReader<MyEntity> fileReader(@Value("${inputFile}") Resource inputFile){
		FlatFileItemReader<MyEntity> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(inputFile);
        csvFileReader.setLinesToSkip(1);
        
       /* LineMapper<MyEntity> campaignLineMapper = campaignLineMapper();
        csvFileReader.setLineMapper(campaignLineMapper);*/
		
		return csvFileReader;
	}
	
	@Bean
	public JdbcBatchItemWriter<MyEntity> jdbcWriter(DataSource ds) {
		JdbcBatchItemWriter<MyEntity> jdbcWriter = new JdbcBatchItemWriter<>();
		
		
	}
	
}
