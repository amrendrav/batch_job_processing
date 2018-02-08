package com.bulkprocess.bulkprocess.writers;

import com.bulkprocess.bulkprocess.pojo.ReadAndWritePojo;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * @author Amrendra Vimal
 */

@Configuration
public class InputFileWriterConfiguration {
	
	@Autowired
	DataSource datasource;

	@Bean
	public JdbcBatchItemWriter<ReadAndWritePojo> jdbcWriter() {
		JdbcBatchItemWriter<ReadAndWritePojo> jdbcWriter = new JdbcBatchItemWriter<>();
		jdbcWriter.setSql("insert into ACCT_OFFER_TEST(CR_USER_NM, CLNT_ID, ACCT_NBR) values (:EmailAddress, :CampaignKey, :contact_group)");
		jdbcWriter.setDataSource(datasource);
		jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ReadAndWritePojo>());

		return jdbcWriter;

	}


}
