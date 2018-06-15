package com.amrendra.batchjob;

import com.amrendra.batchjob.utility.StatusesMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {BatchJobProcessingApplication.class})
public class StatusMapTest {
    @Autowired
    private StatusesMap statusesMap;

    @Test
    public void testClientOfferStatusSize(){
        Assert.assertTrue(statusesMap.getClientOfferStatuses().keySet().size() == 3);
    }

    @Test
    public void testAcctOfferTierStatusSize(){
        Assert.assertTrue(statusesMap.getAccountOfferTierStatuses().keySet().size() == 6);
    }

    @Test
    public void testAcctOfferStatusSize(){
        Assert.assertTrue(statusesMap.getAccountOfferStatuses().keySet().size() == 5);
    }

    @Test
    public void testAcctOfferAwardStatusSize(){
        Assert.assertTrue(statusesMap.getAccountOfferAwardStatuses().keySet().size() == 2);
    }
}
