package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveLoginTests {

    @Test
    public void TestLoginFunctionality() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameInputField = driver.findElement(By.xpath("//input[@id='username']"));
        WebElement passwordInputField = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));

        usernameInputField.sendKeys("student");
        passwordInputField.sendKeys("Password123");
        submitButton.click();

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
}
