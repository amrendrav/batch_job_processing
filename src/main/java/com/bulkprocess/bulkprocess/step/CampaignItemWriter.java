package com.bulkprocess.bulkprocess.step;

import com.bulkprocess.bulkprocess.domain.CampaignTO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class CampaignItemWriter {



   /* @Override
    public void write(List<? extends CampaignTO> items) throws Exception {
        System.out.println(items.toString());
        *//*JdbcBatchItemWriter<CampaignTO> jdbcWriter = new JdbcBatchItemWriter<>();
        jdbcWriter.setSql("insert into ACCT_OFFER_TEST(CR_USER_NM, CLNT_ID, ACCT_NBR) values (:EmailAddress, :CampaignKey, :contact_group)");
        jdbcWriter.setDataSource(datasource);
        jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CampaignTO>());*//*

    }*/
   public JdbcBatchItemWriter<CampaignTO> jdbcWriter(DataSource dataSource) {
       JdbcBatchItemWriter<CampaignTO> jdbcWriter = new JdbcBatchItemWriter<>();
       jdbcWriter.setSql("insert into ACCT_OFFER_TEST(CR_USER_NM, CLNT_ID, ACCT_NBR) values (:EmailAddress, :CampaignKey, :contact_group)");
       jdbcWriter.setDataSource(dataSource);
       jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CampaignTO>());
       //jdbcWriter.setJdbcTemplate(namedParamJdbcTemplate);



/*ItemSqlParameterSourceProvider<CampaignTO> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		jdbcWriter.setItemSqlParameterSourceProvider(paramProvider);
*/

		return jdbcWriter;

	}

}
