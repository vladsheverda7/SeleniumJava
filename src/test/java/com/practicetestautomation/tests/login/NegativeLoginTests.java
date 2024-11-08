package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class NegativeLoginTests {
    @Test
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

    @Test
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
