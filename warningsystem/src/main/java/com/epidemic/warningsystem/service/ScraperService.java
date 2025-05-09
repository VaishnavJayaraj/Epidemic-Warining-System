package com.epidemic.warningsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

//Scrape confirmed cases
@Service
public class ScraperService {
	
public static final String URL = "https://arcg.is/0fHmTX";     // URL of the COVID-19 tracking dashboard
	
	// This method scrapes the COVID-19 case data from the specified website using Selenium WebDriver and returns it as a Map
    public Map<String, Integer> getConfirmedCases() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");  // Set the path to your local chromedriver
        WebDriver driver = new ChromeDriver();                                 // Launch a new Chrome browser instance
        driver.get(URL);                                                      // Open the COVID-19 dashboard
        
        try {
            Thread.sleep(5000); // Wait to ensure page content is fully loaded

            List<WebElement> confirmed = driver.findElements(By.xpath("//*[@id='ember32']")); // Locate confirmed case containers
            Map<String, Integer> countriesConfirmed = new HashMap<>(); // Holds mapping of countries to case counts

            // Parse and store country data
            for (WebElement element : confirmed) {
                List<WebElement> countries = element.findElements(By.className("external-html"));
                for (WebElement country : countries) {
                    String[] parts = country.getText().split(" ", 2); // Split string into count and country name
                    if (parts.length == 2) {
                        countriesConfirmed.put(parts[1], Integer.parseInt(parts[0].replace(",", ""))); // Clean and parse number
                    }
                }
            }
            return countriesConfirmed; // Return the final map
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>(); // Return empty map on error
        } finally {
            driver.quit(); // Always close the browser
        }
    }
    
    // This method extracts the last updated timestamp from the dashboard
    public String getLastUpdatedTimestamp() {
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        try {
            Thread.sleep(5000);    // Ensure full page load
            WebElement ts = driver.findElement(By.xpath("//*[@id='ember46']/div")); // Find timestamp element
            return ts.getText();
        } catch (Exception e) {
            return "Unknown timestamp"; // Fallback if timestamp isn't found
        } finally {
            driver.quit(); // Close browser
        }
    }

}
