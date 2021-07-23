package com.breakthrough.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.breakthrough.scheduler.dto.PaymentDTO;

public class SimpleJob extends QuartzJobBean {

	@Value("${jms.queue.destination}")
	private String queue;
	
	@Autowired
	private JmsTemplate jmsTemplate;

	// SpringBeanJobFactory will inject job properties with JobDataMap
	private String companyName;
	private String companyId;
	private int billingDay;
	private int paymentDay;
	
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
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		PaymentDTO paymentDTO = new PaymentDTO();
		
		paymentDTO.setCompanyName(companyName);
		paymentDTO.setCompanyId(companyId);
		paymentDTO.setBillingDay(billingDay);
		paymentDTO.setPaymentDay(paymentDay);
		
		this.jmsTemplate.send(queue, session -> session.createObjectMessage(paymentDTO));				
		
	}

}
