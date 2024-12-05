package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private By usernameInputLocator = By.xpath("//input[@id='username']");
    private By passwordInputLocator = By.xpath("//input[@id='password']");
    private By submitButtonLocator = By.xpath("//button[@id='submit']");
    private By errorMessageLocator = By.id("error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void visit() {
        super.visit("https://practicetestautomation.com/practice-test-login/");
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

    public SuccessfulLoginPage executeLogin(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickSubmitButton();

        return new SuccessfulLoginPage(driver);
    }

    public String getErrorMessage() {
        WebElement errorMessageElement = waitForElement(errorMessageLocator);
        return errorMessageElement.getText();
    }
}
