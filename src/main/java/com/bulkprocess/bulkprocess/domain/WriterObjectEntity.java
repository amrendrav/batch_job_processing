package com.bulkprocess.bulkprocess.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Amrendra Vimal
 */

@Entity
@Table(name="ACCT_OFFER_TEST")
public class WriterObjectEntity {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACCT_OFFER_ID")
	private Long acctOfferId;
	
	@Column(name="ACCT_NBR")
	private String acctNumber;
	
	@Column(name="CLNT_ID")
	private Long clientId;

	public WriterObjectEntity() {
		// TODO Auto-generated constructor stub
	}

	public WriterObjectEntity(Long acctOfferId, Integer offerID, Long acctID, String acctNumber, String createUser,
							  LocalDateTime createTS, short acctOfferStatusID, Long clientId, String accountNumber,
							  Long accountOfferStatusTimeStamp) {
		super();
		this.acctOfferId = acctOfferId;
		this.acctNumber = acctNumber;
		this.clientId = clientId;
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

	
}
