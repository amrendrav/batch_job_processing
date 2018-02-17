package com.amrendra.batchjob.writers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author Amrendra Vimal
 */

public class ScheduledBatchJobItemWriter implements ItemWriter<String> {

	public final static Logger logger = LoggerFactory.getLogger(ScheduledBatchJobItemWriter.class);

	//@Override
	public void write(List<? extends String> messages) throws Exception {
		for (String message : messages) {
			logger.info("Inside Batch Writer Job Writing -> " + message);
		}
	}

}
