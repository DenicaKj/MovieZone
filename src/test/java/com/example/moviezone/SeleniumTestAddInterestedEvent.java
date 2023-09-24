package com.example.moviezone;

import com.example.moviezone.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest()
public class SeleniumTestAddInterestedEvent {

    private HtmlUnitDriver driver;

    @Autowired
    UserService userService;


    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    @Test
    public void testAddEventToInterestedAndCheckIfDisplayed() {
        driver.get("http://localhost:9091/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Find the username and password input fields and submit button
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        // Enter valid username and password
        usernameInput.sendKeys("dena");
        passwordInput.sendKeys("dena");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", submitButton);

        // Navigate to the add film page
        driver.get("http://localhost:9091/home/getEvent/44"); // Replace with the actual URL

        submitButton = driver.findElement(By.id("submit"));

        jsExecutor.executeScript("arguments[0].click();", submitButton);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + driver.getCurrentUrl());
        List<WebElement> eventCardElements = driver.findElements(By.cssSelector("div.card"));

        int displayedEventCount = eventCardElements.size();

        assertTrue(displayedEventCount > 0, "At least one event is displayed");
        // Check if the film is displayed on the films page
        WebElement eventElement = driver.findElement(By.xpath("//h2[text()='Bithday']"));
        assertTrue(eventElement.isDisplayed(), "Event is displayed on the user page");

        submitButton = driver.findElement(By.id("submit"));

        jsExecutor.executeScript("arguments[0].click();", submitButton);

        eventCardElements = driver.findElements(By.cssSelector("div.card"));

        displayedEventCount = eventCardElements.size();

        assertTrue(displayedEventCount==0, "Event is not displayed on the user page");
    }
}