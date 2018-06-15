package com.amrendra.batchjob;

import com.amrendra.batchjob.utility.StatusesMap;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Amrendra Vimal
 */

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class BatchJobProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchJobProcessingApplication.class, args);
		StatusesMap s = new StatusesMap();
		System.out.println("MC" + s.getClientOfferStatuses().keySet().size());
	}



}
