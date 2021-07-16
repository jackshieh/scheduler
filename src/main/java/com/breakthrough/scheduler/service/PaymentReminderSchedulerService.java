package com.breakthrough.scheduler.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;

import com.breakthrough.scheduler.common.CronUtils;
import com.breakthrough.scheduler.common.DateTool;
import com.breakthrough.scheduler.common.ObjectUtils;
import com.breakthrough.scheduler.common.PaymentTerm;
import com.breakthrough.scheduler.job.PaymentReminderJob;
import com.breakthrough.scheduler.model.PaymentReminder;
import com.sun.istack.NotNull;

@Service
public class PaymentReminderSchedulerService extends BaseSchedulerService<PaymentReminderJob, PaymentReminder> {
	
	public PaymentReminderSchedulerService() {
		super();
	}

	/**
	 * Build {@link JobDetail} with our data model
	 * @param paymentReminder the scheduling model
	 */
	@Override
	protected JobDetail buildJobDetail(final PaymentReminder model) {
//		final String jobName = this.generateJobName();
//		final String jobGroup = this.getJobGroup();
		return JobBuilder.newJob().withIdentity(this.jobKey).setJobData(this.buildJobDataMap(model))
				.storeDurably().build();
	}

	/**
	 * Build the trigger
	 * @param paymentReminder the scheduling model
	 */
	@Override
	protected Trigger buildTrigger(PaymentReminder model)  throws Exception {
		final String DATE_FORMAT = "yyyy/MM/dd";
		Date start = DateTool.toDate(model.getStartingDate(), DATE_FORMAT);
		Date end = DateTool.toDate(model.getEndDate(), DATE_FORMAT);		
		return TriggerBuilder.newTrigger().forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
				.withSchedule(CronScheduleBuilder.cronSchedule(this.buildCronExpression(model)).withMisfireHandlingInstructionDoNothing())
				.startAt(start).endAt(end).build();
	}
	
	/**
	 * Get the cron expression based on the given {@link PaymentReminder}
	 * @param paymentReminder the scheduling model
	 * @return cronExpression cron expression
	 */
	private String buildCronExpression(@NotNull final PaymentReminder paymentReminder) throws Exception {
			String cronExpression = null;
			String paymentTerm = paymentReminder.getPaymentTerm().trim();
			LocalDate scheduledDate = getScheduledDate(paymentReminder);
			
			if (paymentTerm.equalsIgnoreCase(PaymentTerm.CURRENT_MONTH.getChinese())) {			
				cronExpression = CronUtils.executeMonthly(0, 0, scheduledDate.getDayOfMonth());
			} else if (paymentTerm.equalsIgnoreCase(PaymentTerm.EVERY_OTHER_MONTH.getChinese())) {
				cronExpression = CronUtils.excuteEveryOtherMonth(0, 0, scheduledDate.getDayOfMonth());
			}
			
			logger.info("cron expression: " + cronExpression);
			return cronExpression;		
	}
	
	/**
	 * Calculate when to schedule the job
	 * @param paymentReminder the data model
	 * @return
	 */
	private LocalDate getScheduledDate(final PaymentReminder paymentReminder) throws Exception {
		Date scheduledDate = DateUtils.addDays(DateTool.toDate(paymentReminder.getStartingDate(), "yyyy/MM/dd"), - paymentReminder.getDaysScheduled());
		logger.info("scheduled date" + scheduledDate);
		return DateTool.toLocalDate(scheduledDate);		
	}

	/**
	 * Convert our data model into {@link JobDataMap}
	 * @param model the scheduling model
	 */
	@Override
	protected JobDataMap buildJobDataMap(PaymentReminder model) {
		Map<String, Object> dataMap = ObjectUtils.toMap(model);
		return new JobDataMap(dataMap);
	}

	public static void  main(String[] args) {
		PaymentReminderSchedulerService ps = new PaymentReminderSchedulerService();
		System.out.println(ps.getJobClass().getName());
		System.out.println(ps.getJobClass().getSimpleName());
	}
	
}
