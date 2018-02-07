package com.bulkprocess.bulkprocess.step;

import com.bulkprocess.bulkprocess.domain.CampaignTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

public class CampaignItemReader {




    /*@Override
    public CampaignTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        FlatFileItemReader<CampaignTO> csvFileReader = new FlatFileItemReader<>();

        csvFileReader.setResource(new ClassPathResource("/epsilon.txt"));
        csvFileReader.setLinesToSkip(1);

        LineMapper<CampaignTO> campaignLineMapper = campaignLineMapper();
        csvFileReader.setLineMapper(campaignLineMapper);

        return new CampaignTO();
    }*/
    public FlatFileItemReader<CampaignTO> fileReader(String inputFile){
        FlatFileItemReader<CampaignTO> csvFileReader = new FlatFileItemReader<>();
//
        csvFileReader.setResource(new ClassPathResource(inputFile));
        csvFileReader.setLinesToSkip(1);

        LineMapper<CampaignTO> campaignLineMapper = campaignLineMapper();
        csvFileReader.setLineMapper(campaignLineMapper);

        return csvFileReader;
    }

    private LineMapper<CampaignTO> campaignLineMapper() {
        DefaultLineMapper<CampaignTO> campaignLineMapper = new DefaultLineMapper<>();

        LineTokenizer campaignLineTokenizer = createCampaignLineTokenizer();
        campaignLineMapper.setLineTokenizer(campaignLineTokenizer);

        FieldSetMapper<CampaignTO> campaignInformationMapper = createCampaignInformationMapper();
        campaignLineMapper.setFieldSetMapper(campaignInformationMapper);

        return campaignLineMapper;
    }

    private LineTokenizer createCampaignLineTokenizer() {
        DelimitedLineTokenizer campaignLineTokenizer = new DelimitedLineTokenizer();
        int[] requiredColumnPositions = {0,1,9};
        campaignLineTokenizer.setDelimiter("\t");
        //campaignLineTokenizer.setNames(new String[]{"CustomerKey", "EmailAddress", "acct_mask", "ofc_nm", "first_nm", "last_nm", "export_date_today", "campaignname", "template_name", "contact_group", "access_cd", "client_id", "segment_cd", "start_dt", "end_dt", "dynamic1", "dynamic2"});
        campaignLineTokenizer.setIncludedFields(requiredColumnPositions);
        campaignLineTokenizer.setNames(new String[]{"CampaignKey", "EmailAddress", "contact_group"});
        return campaignLineTokenizer;
    }

    private FieldSetMapper<CampaignTO> createCampaignInformationMapper() {
        BeanWrapperFieldSetMapper<CampaignTO> campaignInformationMapper = new BeanWrapperFieldSetMapper<>();
        campaignInformationMapper.setTargetType(CampaignTO.class);
        return campaignInformationMapper;
    }

}
