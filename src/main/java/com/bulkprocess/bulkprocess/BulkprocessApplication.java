package com.bulkprocess.bulkprocess;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BulkprocessApplication {

	public final static Logger logger = LoggerFactory.getLogger(BulkprocessApplication.class);

	public static void main(String[] args) {
		
		System.setProperty("inputFile", "file:///" + new File("C:/workspace/bulkprocess/src/main/resources/epsilon.txt").getAbsolutePath());

		long time = System.currentTimeMillis();
		
		SpringApplication.run(BulkprocessApplication.class, args);

		time = System.currentTimeMillis() - time;
		logger.info("Runtime: {} seconds.", ((double)time/1000));
	}

	/*@Bean
	NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}*/
}
