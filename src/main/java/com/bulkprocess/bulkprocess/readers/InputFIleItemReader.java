package com.bulkprocess.bulkprocess.readers;

import com.bulkprocess.bulkprocess.pojo.ReadAndWritePojo;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Amrendra Vimal
 */

public class InputFIleItemReader {


    public FlatFileItemReader<ReadAndWritePojo> fileReader(String inputFile){
        FlatFileItemReader<ReadAndWritePojo> csvFileReader = new FlatFileItemReader<>();
//
        csvFileReader.setResource(new ClassPathResource(inputFile));
        csvFileReader.setLinesToSkip(1);

        LineMapper<ReadAndWritePojo> campaignLineMapper = campaignLineMapper();
        csvFileReader.setLineMapper(campaignLineMapper);

        return csvFileReader;
    }

    private LineMapper<ReadAndWritePojo> campaignLineMapper() {
        DefaultLineMapper<ReadAndWritePojo> campaignLineMapper = new DefaultLineMapper<>();

        LineTokenizer campaignLineTokenizer = createCampaignLineTokenizer();
        campaignLineMapper.setLineTokenizer(campaignLineTokenizer);

        FieldSetMapper<ReadAndWritePojo> campaignInformationMapper = createCampaignInformationMapper();
        campaignLineMapper.setFieldSetMapper(campaignInformationMapper);

        return campaignLineMapper;
    }

    private LineTokenizer createCampaignLineTokenizer() {
        DelimitedLineTokenizer campaignLineTokenizer = new DelimitedLineTokenizer();
        int[] requiredColumnPositions = {0,1,9};
        campaignLineTokenizer.setDelimiter("\t");
        campaignLineTokenizer.setIncludedFields(requiredColumnPositions);
        campaignLineTokenizer.setNames(new String[]{"CampaignKey", "EmailAddress", "contact_group"});
        return campaignLineTokenizer;
    }

    private FieldSetMapper<ReadAndWritePojo> createCampaignInformationMapper() {
        BeanWrapperFieldSetMapper<ReadAndWritePojo> campaignInformationMapper = new BeanWrapperFieldSetMapper<>();
        campaignInformationMapper.setTargetType(ReadAndWritePojo.class);
        return campaignInformationMapper;
    }

}
