package com.bulkprocess.bulkprocess;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class BulkprocessApplication {

	public final static Logger logger = LoggerFactory.getLogger(BulkprocessApplication.class);

	public static void main(String[] args) {
		

		SpringApplication.run(BulkprocessApplication.class, args);
	}

}
