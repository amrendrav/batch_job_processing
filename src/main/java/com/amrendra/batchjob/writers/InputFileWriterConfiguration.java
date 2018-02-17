package com.amrendra.batchjob.writers;

import com.amrendra.batchjob.pojo.ReadAndWritePojo;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
