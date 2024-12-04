package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessfulLoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By logoutButtonLocator = By.linkText("Log out");

    public SuccessfulLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public Boolean isLogoutDisplayed() {
        try {
            return driver.findElement(logoutButtonLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
