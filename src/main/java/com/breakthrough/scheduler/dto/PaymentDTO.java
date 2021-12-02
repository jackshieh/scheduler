package com.breakthrough.scheduler.dto;

import java.io.Serializable;

import com.breakthrough.scheduler.common.PaymentTerm;

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
	private String projectName;
	private int billingDay;
	private int paymentDay;
	private String paymentTerm = PaymentTerm.CURRENT_MONTH.getChinese();
}
