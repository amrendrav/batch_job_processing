package com.bulkprocess.bulkprocess;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BulkprocessApplication {

	public static void main(String[] args) {
		
		System.setProperty("inputFile", "file:///" + new File("C:/workspace/bulkprocess/src/main/resources/epsilon.txt").getAbsolutePath());
		
		SpringApplication.run(BulkprocessApplication.class, args);
	}

	/*@Bean
	NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}*/
}
