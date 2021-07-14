package com.breakthrough.scheduler.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO implements Serializable {	
	private static final long serialVersionUID = -2721657250446645126L;
	private String companyName;
	private String companyId;
	private int billingDay;
	private int paymentDay;
	private String paymentTerm;
}
