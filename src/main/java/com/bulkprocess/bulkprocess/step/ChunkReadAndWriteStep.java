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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bulkprocess.bulkprocess.domain.OfferAccountEntity;
import com.bulkprocess.bulkprocess.domain.CampaignTO;

@Configuration
public class ChunkReadAndWriteStep {
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	@Value("${inputFile}")
	Resource inputFile;
	
	@Bean
	public FlatFileItemReader<CampaignTO> fileReader(){
		FlatFileItemReader<CampaignTO> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(inputFile);
        csvFileReader.setLinesToSkip(1);
        
       LineMapper<CampaignTO> campaignLineMapper = campaignLineMapper();
        csvFileReader.setLineMapper(campaignLineMapper);
		
		return csvFileReader;
	}

	private LineMapper<CampaignTO> campaignLineMapper() {
		DefaultLineMapper<CampaignTO> campaignLineMapper = new DefaultLineMapper<>();

		LineTokenizer campaignLineTokenizer = createCampaignLineTokenizer();
		campaignLineMapper.setLineTokenizer(campaignLineTokenizer);

		FieldSetMapper<CampaignTO> campaignInformationMapper = createCampaignInformationMapper();
		campaignLineMapper.setFieldSetMapper(campaignInformationMapper);

		return campaignLineMapper;
	}

	private LineTokenizer createCampaignLineTokenizer() {
		DelimitedLineTokenizer campaignLineTokenizer = new DelimitedLineTokenizer();
		int[] requiredColumnPositions = {0,1};
		campaignLineTokenizer.setDelimiter("\t");
		//campaignLineTokenizer.setNames(new String[]{"CustomerKey", "EmailAddress", "acct_mask", "ofc_nm", "first_nm", "last_nm", "export_date_today", "campaignname", "template_name", "contact_group", "access_cd", "client_id", "segment_cd", "start_dt", "end_dt", "dynamic1", "dynamic2"});
		campaignLineTokenizer.setIncludedFields(requiredColumnPositions);
		campaignLineTokenizer.setNames(new String[]{"key", "emailAddress"});
		return campaignLineTokenizer;
	}

	private FieldSetMapper<CampaignTO> createCampaignInformationMapper() {
		BeanWrapperFieldSetMapper<CampaignTO> campaignInformationMapper = new BeanWrapperFieldSetMapper<>();
		campaignInformationMapper.setTargetType(CampaignTO.class);
		return campaignInformationMapper;
	}

	@Bean
	public JdbcBatchItemWriter<CampaignTO> jdbcWriter() {
		JdbcBatchItemWriter<CampaignTO> jdbcWriter = new JdbcBatchItemWriter<>();
		jdbcWriter.setSql("insert into ACCT_OFFER_TEST(CR_USER_NM, CLNT_ID) values (:emailAddress, :key)");
		jdbcWriter.setDataSource(datasource);
		jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CampaignTO>());
		//jdbcWriter.setJdbcTemplate(namedParamJdbcTemplate);
		

		/*ItemSqlParameterSourceProvider<CampaignTO> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		jdbcWriter.setItemSqlParameterSourceProvider(paramProvider);
*/
		return jdbcWriter;

	}



}
