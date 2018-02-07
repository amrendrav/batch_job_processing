package com.bulkprocess.bulkprocess.domain;

public class CampaignTO {
	
	
	private Long CampaignKey;
	private String EmailAddress;
	private String contact_group;
	
	public CampaignTO(){
		
	}
	
	public CampaignTO(Long campaignKey, String emailAddress, String contact_group) {
		super();
		CampaignKey = campaignKey;
		EmailAddress = emailAddress;
		this.contact_group = contact_group;
	}

	public Long getCampaignKey() {
		return CampaignKey;
	}

	public void setCampaignKey(Long campaignKey) {
		CampaignKey = campaignKey;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public String getContact_group() {
		return contact_group;
	}

	public void setContact_group(String contact_group) {
		this.contact_group = contact_group;
	}

	@Override
	public String toString() {
		return "CampaignTO{" +
				"CampaignKey=" + CampaignKey +
				", EmailAddress='" + EmailAddress + '\'' +
				", contact_group='" + contact_group + '\'' +
				'}';
	}
}
