package com.breakthrough.scheduler.common;

public enum PaymentTerm {
	CURRENT_MONTH("當月"), EVERY_OTHER_MONTH("隔月");
	
	private String chinese;
	
	private PaymentTerm(final String chinese) {
		this.chinese = chinese;
	}
	
	public String getChinese() {
		return this.chinese;
	}
}
