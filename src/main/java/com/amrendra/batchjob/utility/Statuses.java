package com.amrendra.batchjob.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vim758 on 6/2/18.
 */
@Component
@ConfigurationProperties("statuses")
public class Statuses {

    private Map<String, Integer> clientOfferStatuses = new HashMap<>();

    public Map<String, Integer> getClientOfferStatuses() {
        return clientOfferStatuses;
    }

    public void setClientOfferStatuses(Map<String, Integer> clientOfferStatuses) {
        this.clientOfferStatuses = clientOfferStatuses;
    }
}
