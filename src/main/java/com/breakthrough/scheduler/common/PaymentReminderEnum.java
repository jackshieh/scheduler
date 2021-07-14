package com.breakthrough.scheduler.common;

import java.util.Date;

public enum PaymentReminderEnum {
	COMPANY_NAME("companyName", String.class), //
	COMPANY_ID("companyId", String.class), //
	STARTING_DATE("startingDate", Date.class), //
	END_DATE("endDate", Date.class), //
	BILLING_DAY("billingDay", Integer.class), //
	PAYMENT_DAY("paymentDay", Integer.class), //
	PAYMENT_TERM("paymentTerm", String.class), //
	DAY_SCHEDULED("daysScheduled", Integer.class);
	
	private String key;
	private Class<?> classType;
	
	private PaymentReminderEnum(final String key, final Class<?> classType) {
		this.key = key;
		this.classType = classType;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public Class<?> getClassType() {
		return this.classType;
	}
	
	public Object getObject() throws Exception, IllegalAccessException {
		return  this.classType.newInstance();
	}
}
