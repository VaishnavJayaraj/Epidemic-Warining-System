# Epidemic Warning System

A Java-based Spring Boot application that scrapes real-time epidemic data and sends SMS alerts when predefined thresholds are exceeded.

## Features

- Scrapes COVID-19 data from [Esri's ArcGIS Dashboard] (https://arcg.is/0fHmTX)
- Monitors specific countries (e.g., US, Sweden)
- Sends SMS alerts when cases exceed set limits
- Periodic checking every hour using `@Scheduled`
- Manual alert testing endpoint at `/test-alert`

## 🧰 Technologies Used

- Java 21
- Spring Boot
- Selenium WebDriver
- Twilio SMS API
- Maven (dependency management)
- ChromeDriver (browser automation)
- REST API (Spring Web)

## 📂 Project Structure

com.example.epidemic
├── EpidemicWarningApplication.java
├── controller
│ └── AlertController.java
├── service
│ ├── ScraperService.java
│ ├── SmsAlertService.java
│ └── SchedulerService.java
