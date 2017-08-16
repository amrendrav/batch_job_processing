package com.bulkprocess.bulkprocess;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BulkprocessApplication {

	public static void main(String[] args) {
		
		System.setProperty("inputFile", "file://" + new File("/workspace/bulkprocess/src/main/resources/campaignFileList.csv").getAbsolutePath());
		
		SpringApplication.run(BulkprocessApplication.class, args);
	}
}
