package com.scheduler.daily_challange_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.scheduler.daily_challange_scheduler.entity")
public class DailyChallangeSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyChallangeSchedulerApplication.class, args);
	}

}
