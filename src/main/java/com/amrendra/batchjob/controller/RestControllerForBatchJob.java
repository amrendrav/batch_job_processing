package com.amrendra.batchjob.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Amrendra Vimal
 */


/**
 * I ahd exposed two of the batch jobs as a service
 * 1. inputFileProcessJob - reads from a file and put it into database.
 * 2. inputFromDatabaseProcessJob - reads from database and dumps the data into another db table.
 */
@RestController
@RequestMapping("/batchJob")
public class RestControllerForBatchJob {

    @Autowired
    private JobLauncher jobLauncher;

	@Autowired
    private ApplicationContext applicationContext;

	@GetMapping(path = "/startFileProcessing", headers = "Accept=application/json", produces = "application/json")
	public String startJob() throws Exception {

		JobParameters jobParameters = new JobParametersBuilder().addLong(
				"time", System.currentTimeMillis()).toJobParameters();
		jobLauncher.run((Job)applicationContext.getBean("inputFileProcessJob"), jobParameters);

		return "File processing Job completed";
	}

    @GetMapping(path = "/startDBProcessing", headers = "Accept=application/json", produces = "application/json")
    public String startDbReadingAndWritingJob() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().addLong(
                "time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run((Job)applicationContext.getBean("inputFromDatabaseProcessJob"), jobParameters);

        return "Reading and Writing completed";
    }


    @GetMapping(path = "/deleteJob", headers = "Accept=application/json", produces = "application/json")
    public String startDbReadingAndDeleteJob() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().addLong(
                "time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run((Job)applicationContext.getBean("inputDeleteDataJob"), jobParameters);

        return "Reading andDeleting completed";
    }

}
