package com.amrendra.batchjob.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vim758 on 6/2/18.
 */
@Configuration
@ConfigurationProperties("statuses")
@EnableConfigurationProperties(StatusesMap.class)
public class StatusesMap {

    private final Map<String, Short> clientOfferStatuses = new HashMap();

    private final Map<String, Short> accountOfferTierStatuses = new HashMap();

    private final Map<String, Short> accountOfferStatuses = new HashMap();

    private final Map<String, Short> accountOfferAwardStatuses = new HashMap();

    public Map<String, Short> getClientOfferStatuses() {
        return clientOfferStatuses;
    }

    public Map<String, Short> getAccountOfferTierStatuses() {
        return accountOfferTierStatuses;
    }

    public Map<String, Short> getAccountOfferStatuses() {
        return accountOfferStatuses;
    }

    public Map<String, Short> getAccountOfferAwardStatuses() {
        return accountOfferAwardStatuses;
    }
}
