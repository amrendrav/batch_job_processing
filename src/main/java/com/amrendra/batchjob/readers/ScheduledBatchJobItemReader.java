package com.amrendra.batchjob.readers;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author Amrendra Vimal
 */

public class ScheduledBatchJobItemReader implements ItemReader<String> {

	private String[] messages = { "Hi there - This message is printed through Schedular batchJob execution." };

	private int count = 0;

//	@Override
	public String read(){

		if (count < messages.length) {
			return messages[count++];
		} else {
			count = 0;
		}
		return null;
	}

}
