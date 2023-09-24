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

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest()
public class SeleniumTestAddFilm {

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
    public void testAddFilmAndCheckIfDisplayed() {
        driver.get("http://localhost:9091/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Find the username and password input fields and submit button
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        // Enter valid username and password
        usernameInput.sendKeys("admin");
        passwordInput.sendKeys("admin");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", submitButton);

        // Navigate to the add film page
        driver.get("http://localhost:9091/addFilm"); // Replace with the actual URL

        // Fill in the film details
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement durationInput = driver.findElement(By.id("duration"));
        WebElement actorsInput = driver.findElement(By.id("actors"));
        WebElement genreInput = driver.findElement(By.id("genre"));
        WebElement ageCategoryInput = driver.findElement(By.id("age_category"));
        WebElement urlInput = driver.findElement(By.id("url"));
        WebElement directorInput = driver.findElement(By.id("director"));
        WebElement startDateInput = driver.findElement(By.id("start_date"));
        WebElement endDateInput = driver.findElement(By.id("end_date"));
        submitButton = driver.findElement(By.id("submit"));

        nameInput.sendKeys("Sample Film");
        durationInput.sendKeys("120");
        actorsInput.sendKeys("Actor 1, Actor 2");
        genreInput.sendKeys("Action");
        ageCategoryInput.sendKeys("PG-13");
        urlInput.sendKeys("https://example.com/sample.jpg");
        directorInput.sendKeys("Director Name");
        startDateInput.sendKeys("2023-10-01");
        endDateInput.sendKeys("2023-10-31");

        jsExecutor.executeScript("arguments[0].click();", submitButton);

        // After adding the film, navigate to the films page
        driver.get("http://localhost:9091/home/films"); // Replace with the actual URL

        // Check if the film is displayed on the films page
        WebElement filmElement = driver.findElement(By.xpath("//h2[text()='Sample Film']"));
        assertTrue(filmElement.isDisplayed(), "Film is displayed on the films page");
    }
}