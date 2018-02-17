package com.amrendra.batchjob.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * @author Amrendra Vimal
 */

public class SchedularBatchJobCompletionListener extends JobExecutionListenerSupport {
	public final static Logger logger = LoggerFactory.getLogger(SchedularBatchJobCompletionListener.class);

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			logger.info("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}
