package com.epidemic.warningsystem.service;

import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//Periodic alert checker
@Service
public class SchedulerService {
	private final ScraperService scraperService;
    private final SmsAlertService smsAlertService;

    private final String[] countries = {"US", "Sweden"};   // List of countries to monitor
    private final int[] thresholds = {12, 1};             // Corresponding thresholds for each country

    public SchedulerService(ScraperService scraperService, SmsAlertService smsAlertService)  //Dependency Injection
    {
        this.scraperService = scraperService;
        this.smsAlertService = smsAlertService;
    }

    @Scheduled(fixedRate = 3600000)        // Schedules this method to run repeatedly every 3600000 milliseconds (1 hour)
    public void checkStatus() {
    	
        // This method checks if any monitored country exceeds its threshold and sends an SMS alert if necessary.
        Map<String, Integer> confirmed = scraperService.getConfirmedCases(); // Scrape latest case data
        String ts = scraperService.getLastUpdatedTimestamp(); // Fetch last updated timestamp

        // Iterate over each country and compare case count with threshold
        for (int i = 0; i < countries.length; i++) {
            String country = countries[i];
            int threshold = thresholds[i];

            if (confirmed.getOrDefault(country, 0) >= threshold) { // Check if alert should be triggered
                String msg = String.format("Alert Triggered! %s has %d confirmed cases.\nFor more info: %s\n%s",
                        country, confirmed.get(country), "https://arcg.is/1DPOWm0", ts);
                smsAlertService.sendSms(msg); // Send SMS alert
            } else {
                System.out.println(country + " did not trigger an alert."); // Log non-trigger event
            }
        }
    }
	
	

}
