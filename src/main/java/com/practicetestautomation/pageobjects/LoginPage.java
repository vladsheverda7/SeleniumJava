package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private WebDriverWait wait;
    private final By usernameInputLocator = By.xpath("//input[@id='username']");
    private final By passwordInputLocator = By.xpath("//input[@id='password']");
    private final By submitButtonLocator = By.xpath("//input[@id='submit']");
    private final By errorMessageLocator = By.id("error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    private void enterUserName(String username) {
        driver.findElement(usernameInputLocator).sendKeys(username);
    }

    private void enterPassword(String password) {
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    private void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }

    public void executeLogin(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickSubmitButton();
    }

    public String getErrorMessage() {
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorMessageElement.getText();
    }
}
