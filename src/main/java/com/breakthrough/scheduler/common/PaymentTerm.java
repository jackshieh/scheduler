package com.breakthrough.scheduler.common;

public enum PaymentTerm {
	CURRENT_MONTH("ηΆζ"), EVERY_OTHER_MONTH("ιζ");
	
	private String chinese;
	
	private PaymentTerm(final String chinese) {
		this.chinese = chinese;
	}
	
	public String getChinese() {
		return this.chinese;
	}
}
