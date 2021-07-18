package com.breakthrough.scheduler.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;

import com.breakthrough.scheduler.controller.PaymentReminderScheduleController;

import lombok.extern.slf4j.Slf4j;

import org.quartz.TriggerListener;

@Slf4j
public class PaymentReminderTriggerListener implements TriggerListener {

	@Override
	public String getName() {		
		return this.getClass().getSimpleName();
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		String jobName = trigger.getKey().getName();
		log.info("{} fired at {}", jobName, trigger.getStartTime());
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {		
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		log.info("trigger {} misfired at {}", trigger.getKey().getName(), trigger.getStartTime());
	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		log.info("trigger {} completed at {}", trigger.getKey().getName(), trigger.getEndTime());
	}

}
