package com.example.moviezone;

import com.example.moviezone.model.Role;
import com.example.moviezone.model.User;
import com.example.moviezone.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.TimeUnit;


@SpringBootTest()
public class SeleniumTestLogin {

    private HtmlUnitDriver driver;

    private static boolean dataInitialized = false;

    private static User regularUser;

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
    public void testLoginWithValidCredentials() {
        // Navigate to the login page
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

        // Verify if the user is redirected to the home page (change the URL as needed)
        String currentUrl = driver.getCurrentUrl();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        String actualUsername = driver.findElement(By.id("username")).getText();
        System.out.println(actualUsername);
        assert currentUrl.equals("http://localhost:9091/home");
        Assert.assertEquals(actualUsername, "dena");

        WebElement logoutButton = driver.findElement(By.id("logout"));
        jsExecutor.executeScript("arguments[0].click();", logoutButton);

        assert driver.findElement(By.id("loginPage")).isDisplayed();
    }

    @Test
    public void testHomeToLogin() {
        driver.get("http://localhost:9091/home");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement loginButton = driver.findElement(By.id("loginPage"));


        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", loginButton);

        // Verify if the user is redirected to the home page (change the URL as needed)
        String currentUrl = driver.getCurrentUrl();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        assert currentUrl.equals("http://localhost:9091/login");
    }
}
