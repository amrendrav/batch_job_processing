package com.bulkprocess.bulkprocess.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batchRestJob")
public class RestControllerForBatchJob {

    @Autowired
    JobLauncher jobLauncher;


	@Autowired
    ApplicationContext applicationContext;

	@RequestMapping(path = "/startJob", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public String startJob() throws Exception {

		JobParameters jobParameters = new JobParametersBuilder().addLong(
				"time", System.currentTimeMillis()).toJobParameters();
		jobLauncher.run((Job)applicationContext.getBean("processJob"), jobParameters);

		return "Batch job has been invoked";
	}

}
