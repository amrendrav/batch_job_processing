package com.amrendra.batchjob;

import com.amrendra.batchjob.utility.Statuses;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	Statuses statuses;

	public static void main(String[] args) {
		SpringApplication.run(BatchJobProcessingApplication.class, args);
		Statuses s = new Statuses();
		System.out.println(s.getClientOfferStatuses().keySet().size());
	}



}
