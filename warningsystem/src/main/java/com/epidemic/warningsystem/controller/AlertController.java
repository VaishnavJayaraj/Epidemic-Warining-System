package com.epidemic.warningsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epidemic.warningsystem.service.SchedulerService;

//To trigger test alert manually via endpoint
@RestController
public class AlertController {
	
	 private final SchedulerService schedulerService;

	    public AlertController(SchedulerService schedulerService) {
	        this.schedulerService = schedulerService;
	    }

	    @GetMapping("/test-alert") // This is a REST endpoint to manually trigger the alert check. Useful for testing without waiting for the scheduler.
	    public String testAlert() {
	        schedulerService.checkStatus(); // Invoke checkStatus logic
	        return "Test alert triggered!"; // Response message confirming the test alert execution
	    }

}
