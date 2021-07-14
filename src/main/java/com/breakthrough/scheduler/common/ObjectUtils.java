package com.breakthrough.scheduler.common;

import java.util.Date;
import java.util.Map;

import com.breakthrough.scheduler.model.PaymentReminder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;

public class ObjectUtils {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(@NotNull Object object) {
		ObjectMapper mapper = new ObjectMapper();		
		return mapper.convertValue(object, Map.class);
	}
	
	public static void main(String[] args) throws Exception {
		PaymentReminder reminder = new PaymentReminder();
		reminder.setBillingDay(5);
		reminder.setCompanyName("Breakthrough");
		reminder.setDaysScheduled(3);
		reminder.setEndDate(new Date());
		reminder.setPaymentDay(15);
		reminder.setStartingDate(new Date());
		System.out.println(ObjectUtils.toMap(reminder));
		
	}
}
