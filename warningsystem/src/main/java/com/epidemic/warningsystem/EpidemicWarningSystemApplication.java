package com.epidemic.warningsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling     // Enables Spring's scheduling capabilities to allow scheduled tasks via @Scheduled annotations.
public class EpidemicWarningSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpidemicWarningSystemApplication.class, args);
	}

}
