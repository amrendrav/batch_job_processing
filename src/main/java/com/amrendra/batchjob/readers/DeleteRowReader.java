package com.amrendra.batchjob.readers;

import org.springframework.batch.item.ItemReader;

public class DeleteRowReader implements ItemReader<Integer> {

    private String[] messages = { "Hi there - This message is printed through Schedular batchJob execution." };

    private int count = 0;

    //	@Override
    public Integer read(){

        return new Integer(1);
    }


}
