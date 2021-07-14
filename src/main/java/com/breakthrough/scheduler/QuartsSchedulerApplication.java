package com.breakthrough.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class QuartsSchedulerApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(QuartsSchedulerApplication.class);
	}
    
	public static void main(String[] args) {
		SpringApplication.run(QuartsSchedulerApplication.class, args);
	}
	
}
