package com.amrendra.batchjob.writers;

import com.amrendra.batchjob.pojo.DeleteTest;
import com.amrendra.batchjob.pojo.ReadAndWritePojo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class BookJdbcItemWriter {

    private static final String DELETE_BOOK = "delete from delete_test where id = 1";

   /* private JdbcTemplate jdbcTemplate;

    public BookJdbcItemWriter(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void write(List<? extends DeleteTest> items) throws Exception {
        for(DeleteTest item : items) {
            int updated = jdbcTemplate.update(DELETE_BOOK,item.getId());
        }
    }*/

    @Autowired
    DataSource datasource;

    @Bean
    public JdbcBatchItemWriter<DeleteTest> jdbcDelete() {
        JdbcBatchItemWriter<DeleteTest> jdbcWriter = new JdbcBatchItemWriter<>();
        jdbcWriter.setSql(DELETE_BOOK);
        jdbcWriter.setDataSource(datasource);
        jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<DeleteTest>());

        return jdbcWriter;

    }
}