package com.breakthrough.scheduler.service;

import java.util.Map;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;

import com.breakthrough.scheduler.common.ObjectUtils;
import com.breakthrough.scheduler.job.SimpleJob;
import com.breakthrough.scheduler.model.PaymentReminder;

@Service
public class SimpleJobService extends BaseSchedulerService<SimpleJob, PaymentReminder> {

	public SimpleJobService () {
		super();
		logger.info("calling SimpleJobService");
	}
	
	/**
	 * Build JobDeail
	 * Note: don't forget to add this.getJobClass()
	 */
	@Override
	protected JobDetail buildJobDetail(PaymentReminder model) {
		logger.info("calling buildJobDetail");
		return JobBuilder.newJob(this.getJobClass()).withIdentity(this.jobKey).setJobData(this.buildJobDataMap(model))
				.storeDurably().build();
	}

	@Override
	protected Trigger buildTrigger(PaymentReminder model) throws Exception {
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
			    .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
			    .startNow() 
			    .forJob(this.jobDetail)
			    .endAt(DateBuilder.todayAt(22, 0, 0))
			    .build();		
		return trigger;
	}

	@Override
	protected JobDataMap buildJobDataMap(PaymentReminder model) {
		Map<String, Object> dataMap = ObjectUtils.toMap(model);
		return new JobDataMap(dataMap);
	}

}
