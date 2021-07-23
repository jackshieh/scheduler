package com.breakthrough.scheduler.controller;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.breakthrough.scheduler.common.DateTool;
import com.breakthrough.scheduler.model.PaymentReminder;
import com.breakthrough.scheduler.service.SimpleJobService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class SimpleJobController {
	public static final String API = "/simplejob";
	
	@Autowired
	private SimpleJobService simpleJobService;
	
	@RequestMapping(value = API, method = RequestMethod.POST)
	@ResponseBody
	public void scheduleSimpleJob() {
		try {
			PaymentReminder paymentReminder = new PaymentReminder();
			paymentReminder.setCompanyName("Breakthrough Information Inc.");
			paymentReminder.setCompanyId("82821167");
			paymentReminder.setDaysScheduled(5);
			paymentReminder.setStartingDate(DateTool.now());
			paymentReminder.setEndDate(DateTool.toString(DateUtils.addDays(new Date(), 1)));
			this.simpleJobService.doSchedule(paymentReminder);
		} catch(Exception e) {
			e.fillInStackTrace();
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "無法排程");
		}
	}
}
