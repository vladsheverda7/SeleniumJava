package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTests {
    @Test(groups = {"positive", "regression", "smoke"})
    public void TestLoginFunctionality() {
        WebDriver driver = new ChromeDriver();
        // WebDriver driver = new SafariDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameInputField = driver.findElement(By.xpath("//input[@id='username']"));
        WebElement passwordInputField = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));

        usernameInputField.sendKeys("student");
        passwordInputField.sendKeys("Password123");
        submitButton.click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        String expectedMessage = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMessage));

        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOutButton.isDisplayed());

        driver.quit();
    }

    @Test(groups = {"negative", "regression"})
    public void IncorrectUsernameTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameInputField = driver.findElement(By.xpath("//input[@id='username']"));
        usernameInputField.sendKeys("incorrectUser");

        WebElement passwordInputField = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInputField.sendKeys("Password123");

        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));
        submitButton.click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement incorrectUserNameMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(incorrectUserNameMessage.isDisplayed());

        String expectedIncorrectUserNameMessage = "Your username is invalid!";
        String actualIncorrectUserNameMessage = incorrectUserNameMessage.getText();

        Assert.assertEquals(actualIncorrectUserNameMessage, expectedIncorrectUserNameMessage);

        driver.quit();
    }

    @Test(groups = {"negative", "regression"})
    public void IncorrectPasswordTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameInputField = driver.findElement(By.xpath("//input[@id='username']"));
        usernameInputField.sendKeys("student");

        WebElement passwordInputField = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInputField.sendKeys("incorrectPassword");

        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));
        submitButton.click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement incorrectPasswordMessage = driver.findElement(By.xpath("//div[@id='error']"));
        Assert.assertTrue(incorrectPasswordMessage.isDisplayed());

        String expectedIncorrectPasswordMessage = "Your password is invalid!";
        String actualErrorMessage = incorrectPasswordMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedIncorrectPasswordMessage);

        driver.quit();
    }
}
