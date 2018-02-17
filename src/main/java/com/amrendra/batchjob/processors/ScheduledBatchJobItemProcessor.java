package com.amrendra.batchjob.processors;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author Amrendra Vimal
 */

public class ScheduledBatchJobItemProcessor implements ItemProcessor<String, String> {
	//@Override
	public String process(String data) throws Exception {
		return data.toUpperCase();
	}

}

