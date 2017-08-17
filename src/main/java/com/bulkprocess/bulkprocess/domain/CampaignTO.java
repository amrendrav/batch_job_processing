package com.bulkprocess.bulkprocess.domain;

public class CampaignTO {
	
	
	private Long key;
	private String emailAddress;
	
	public CampaignTO(){
		
	}

	public CampaignTO(Long key, String emailAddress) {
		super();
		this.key = key;
		this.emailAddress = emailAddress;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	

	

}
