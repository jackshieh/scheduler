package com.breakthrough.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.breakthrough.scheduler.dto.PaymentDTO;




/**
 * Use {@link QuartzJobBean} instead of the native Job interface
 * @author jack
 *
 */
public class PaymentReminderJob extends QuartzJobBean {
	@Value("${jms.queue.destination}")
	private String queue;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	// SpringBeanJobFactory will inject job properties with JobDataMap
	private String companyName;
	private String companyId;
	private int billingDay;
	private int paymentDay;
	private String paymentTerm;
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setBillingDay(int billingDay) {
		this.billingDay = billingDay;
	}

	public void setPaymentDay(int paymentDay) {
		this.paymentDay = paymentDay;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}


	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {		
		// JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		// send message to Artemis using paymentDTO
		PaymentDTO paymentDTO = new PaymentDTO();
		
		paymentDTO.setCompanyName(this.companyName);
		paymentDTO.setCompanyId(this.companyId);
		paymentDTO.setBillingDay(this.billingDay);
		paymentDTO.setPaymentDay(this.paymentDay);
		paymentDTO.setPaymentTerm(this.paymentTerm);
		
		this.jmsTemplate.send(queue, session -> session.createObjectMessage(paymentDTO));		
	}

}
