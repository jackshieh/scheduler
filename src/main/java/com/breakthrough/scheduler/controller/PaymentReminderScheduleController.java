package com.breakthrough.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.breakthrough.scheduler.listener.PaymentReminderTriggerListener;
import com.breakthrough.scheduler.model.PaymentReminder;
import com.breakthrough.scheduler.service.PaymentReminderSchedulerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class PaymentReminderScheduleController {
	public static final String API = "/schedule";

	// SchedulerFactoryBean.getObject
	@Autowired
	private PaymentReminderSchedulerService paymentReminderSchedulerService;

	@RequestMapping(value = API, method = RequestMethod.POST)
	@ResponseBody
	public void schedulePaymentReminder(final PaymentReminder paymentReminder) {

		try {
			PaymentReminderTriggerListener paymentReminderTriggerListner = new PaymentReminderTriggerListener();
			this.paymentReminderSchedulerService.doSchedule(paymentReminder, paymentReminderTriggerListner);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "無法排程");
		}

	}
}
