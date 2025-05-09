package com.epidemic.warningsystem.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//Send SMS via RESTful API
@Service
public class SmsAlertService {
	
	// Initialize Twilio credentials from environment variables
    private static final String ACCOUNT_SID = System.getenv("TWILIO_SID");
    private static final String AUTH_TOKEN = System.getenv("TWILIO_TOKEN");
    private static final String FROM_NUMBER = System.getenv("TWILIO_NUMBER");
    private static final String TO_NUMBER = System.getenv("PHONE_NUMBER");
    
    // Sends an SMS alert using Twilio API
    public void sendSms(String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); // Authenticate with Twilio using credentials

        Message.creator(
                new PhoneNumber(TO_NUMBER), // Recipient phone number
                new PhoneNumber(FROM_NUMBER), // Sender (Twilio-provided) phone number
                message // SMS body content
        ).create(); // Send the SMS

        System.out.println("SMS sent successfully via Twilio.");
    }
}


