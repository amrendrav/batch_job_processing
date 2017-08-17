package com.bulkprocess.bulkprocess.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ACCT_OFFER_TEST")
public class OfferAccountEntity {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACCT_OFFER_ID")
	private Long acctOfferId;
	
	/*@Column(name="OFFER_ID")
	private Integer offerID;
	
	@Column(name="ACCT_ID")
	private Long acctID;*/
	
	@Column(name="ACCT_NBR")
	private String acctNumber;
	
	/*@Column(name = "CR_USER_NM")
	private String createUser;
	
	@Column(name = "CR_TS")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private LocalDateTime createTS;
	
	@Column(name="ACCT_OFFER_STS_ID")
	private short acctOfferStatusID;*/
	
	@Column(name="CLNT_ID")
	private Long clientId;
	
	/*@Column(name="ACCT_OFFER_STS_TS")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Long accountOfferStatusTimeStamp;*/

	public OfferAccountEntity() {
		// TODO Auto-generated constructor stub
	}

	public OfferAccountEntity(Long acctOfferId, Integer offerID, Long acctID, String acctNumber, String createUser,
			LocalDateTime createTS, short acctOfferStatusID, Long clientId, String accountNumber,
			Long accountOfferStatusTimeStamp) {
		super();
		this.acctOfferId = acctOfferId;
		/*this.offerID = offerID;
		this.acctID = acctID;*/
		this.acctNumber = acctNumber;
		/*this.createUser = createUser;
		this.createTS = createTS;
		this.acctOfferStatusID = acctOfferStatusID;*/
		this.clientId = clientId;
		//this.accountOfferStatusTimeStamp = accountOfferStatusTimeStamp;
	}

	public Long getAcctOfferId() {
		return acctOfferId;
	}

	public void setAcctOfferId(Long acctOfferId) {
		this.acctOfferId = acctOfferId;
	}

	
	public String getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	/*public Long getAccountOfferStatusTimeStamp() {
		return accountOfferStatusTimeStamp;
	}

	public void setAccountOfferStatusTimeStamp(Long accountOfferStatusTimeStamp) {
		this.accountOfferStatusTimeStamp = accountOfferStatusTimeStamp;
	}*/
	
}
