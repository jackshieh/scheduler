package com.breakthrough.scheduler.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentReminder implements Serializable {
	private static final long serialVersionUID = -3351438692200174379L;
	private String companyName;
	private String companyId;
	private Date startingDate;
	private Date endDate;
	private int billingDay;
	private int paymentDay;
	private String paymentTerm;
	private int daysScheduled;
}
