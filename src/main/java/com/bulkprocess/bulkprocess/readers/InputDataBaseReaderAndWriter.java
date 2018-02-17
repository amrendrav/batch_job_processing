package com.bulkprocess.bulkprocess.readers;

import com.bulkprocess.bulkprocess.pojo.RAndWFromDBPOJO;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Amrendra Vimal
 */

@Configuration
public class InputDataBaseReaderAndWriter {

    @Autowired
    private DataSource dataSource;

    @Value("${chunk.size}")
    private int chunkSize;

    /*@Bean
    public JdbcCursorItemReader<RAndWFromDBPOJO> cursorItemReader(){
        JdbcCursorItemReader<RAndWFromDBPOJO> reader = new JdbcCursorItemReader<>();
        reader.setSql("select id, f_name, l_name, phone from user order by f_name");
        reader.setDataSource(this.dataSource);
        reader.setRowMapper(new RAndWFromDBPOJORowMapper());


        return reader;
    }*/


    @Bean
    public JdbcPagingItemReader<RAndWFromDBPOJO> pagingItemReader() {
        JdbcPagingItemReader<RAndWFromDBPOJO> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(chunkSize);
        reader.setRowMapper(new RAndWFromDBPOJORowMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, f_name, l_name, phone");
        queryProvider.setFromClause("from user");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("f_name", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);

        return reader;
    }


    @Bean
    public JdbcBatchItemWriter<RAndWFromDBPOJO> jdbcWriterForDbRWJob() {
        JdbcBatchItemWriter<RAndWFromDBPOJO> jdbcWriter = new JdbcBatchItemWriter<>();
        jdbcWriter.setSql("insert into USER_VIA_BATCH(ID, F_NAME, L_NAME, PHONE) values (:id, :f_name, :l_name, :phone)");
        jdbcWriter.setDataSource(this.dataSource);
        jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return jdbcWriter;
    }
}





