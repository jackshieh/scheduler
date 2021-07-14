package com.breakthrough.scheduler.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.breakthrough.scheduler.common.PaymentReminderEnum;
import com.breakthrough.scheduler.dto.PaymentDTO;


/**
 * Use {@link QuartzJobBean} instead of the native Job interface
 * @author jack
 *
 */
public class PaymentReminderJob extends QuartzJobBean {
	@Value("${jms.queue.destination}")
	String queue;
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {		
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		// send message to Artemis using paymentDTO
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setCompanyName(jobDataMap.getString(PaymentReminderEnum.COMPANY_NAME.getKey()));
		paymentDTO.setCompanyId(jobDataMap.getString(PaymentReminderEnum.COMPANY_ID.getKey()));
		paymentDTO.setBillingDay(jobDataMap.getInt(PaymentReminderEnum.BILLING_DAY.getKey()));
		paymentDTO.setPaymentDay(jobDataMap.getInt(PaymentReminderEnum.PAYMENT_DAY.getKey()));
		paymentDTO.setPaymentTerm(jobDataMap.getString(PaymentReminderEnum.PAYMENT_TERM.getKey()));
		this.jmsTemplate.send(queue, session -> session.createObjectMessage(paymentDTO));		
	}

}
