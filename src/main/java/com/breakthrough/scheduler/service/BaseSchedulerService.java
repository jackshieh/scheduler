package com.breakthrough.scheduler.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.ParameterizedType;
import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSchedulerService <J extends Job, T extends Serializable> {
	protected static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Scheduler scheduler;
	private Class<J> jobClass;
	protected String jobName;
	private String jobGroup;
	protected JobDetail jobDetail;
	protected Trigger trigger;
	protected JobKey jobKey;
	
	@SuppressWarnings("unchecked")
	public BaseSchedulerService() {
		this.jobClass = (Class<J>)  ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.jobGroup = this.jobClass.getSimpleName() + "Group";
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	
	@Autowired
	public final void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public Class<J> getJobClass() {
		return this.jobClass;
	}
	
	public String getJobGroup() {
		return this.jobGroup;
	}
	
	public String generateJobName() {
		return this.getJobClass().getSimpleName() + "-" + UUID.randomUUID().toString();
	}
	
	public JobKey getJobKey() {
		return JobKey.jobKey(this.getJobClass().getSimpleName() + "-" + UUID.randomUUID().toString(), jobGroup);
	}
	
	protected abstract JobDetail buildJobDetail(final T model);
	protected abstract Trigger buildTrigger(final T model) throws Exception;
	protected abstract JobDataMap buildJobDataMap(final T model);
	
	/**
	 * Schedule our target job with {@link JobDetail} and {@link Trigger}
	 * @param T model the scheduling model
	 */	
	public void doSchedule(final T model) {		
		try {
			this.jobKey = this.getJobKey();
			this.jobDetail = buildJobDetail(model);
			this.trigger = buildTrigger(model);			
			this.getScheduler().scheduleJob(jobDetail, trigger);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}		
	}	
	
}
