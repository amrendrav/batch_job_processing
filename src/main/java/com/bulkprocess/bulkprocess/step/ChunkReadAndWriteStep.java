package com.bulkprocess.bulkprocess.step;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.bulkprocess.bulkprocess.domain.MyEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class ChunkReadAndWriteStep {
	@Bean
	public FlatFileItemReader<MyEntity> fileReader(@Value("${inputFile}") Resource inputFile){
		FlatFileItemReader<MyEntity> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(inputFile);
        csvFileReader.setLinesToSkip(1);
        
       LineMapper<MyEntity> campaignLineMapper = campaignLineMapper();
        csvFileReader.setLineMapper(campaignLineMapper);
		
		return csvFileReader;
	}

	@Bean
	public JdbcBatchItemWriter<MyEntity> jdbcWriter(DataSource ds, NamedParameterJdbcTemplate jdbcTemplate) {
		JdbcBatchItemWriter<MyEntity> jdbcWriter = new JdbcBatchItemWriter<>();
		jdbcWriter.setDataSource(ds);
		jdbcWriter.setJdbcTemplate(jdbcTemplate);
		jdbcWriter.setSql("insert into Table (c1, c2, c3) values (:c1, :c2, :c3)");

		ItemSqlParameterSourceProvider<MyEntity> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		jdbcWriter.setItemSqlParameterSourceProvider(paramProvider);

		return jdbcWriter;

	}



	private LineMapper<MyEntity> campaignLineMapper() {
		DefaultLineMapper<MyEntity> campaignLineMapper = new DefaultLineMapper<>();

		LineTokenizer campaignLineTokenizer = createCampaignLineTokenizer();
		campaignLineMapper.setLineTokenizer(campaignLineTokenizer);

		FieldSetMapper<MyEntity> campaignInformationMapper = createCampaignInformationMapper();
		campaignLineMapper.setFieldSetMapper(campaignInformationMapper);

		return campaignLineMapper;
	}

	private LineTokenizer createCampaignLineTokenizer() {
		DelimitedLineTokenizer campaignLineTokenizer = new DelimitedLineTokenizer();
		campaignLineTokenizer.setDelimiter("\t");
		campaignLineTokenizer.setNames(new String[]{"C1", "C2", "C3"});
		return campaignLineTokenizer;
	}

	private FieldSetMapper<MyEntity> createCampaignInformationMapper() {
		BeanWrapperFieldSetMapper<MyEntity> campaignInformationMapper = new BeanWrapperFieldSetMapper<>();
		campaignInformationMapper.setTargetType(MyEntity.class);
		return campaignInformationMapper;
	}



}
