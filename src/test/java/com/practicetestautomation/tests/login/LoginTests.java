package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessfulLoginPage;
import com.practicetestautomation.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = {"positive", "regression", "smoke"})
    public void TestLoginFunctionality() {
        logger.info("Starting test login functionality");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();

        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("student", "Password123");
        successfulLoginPage.load();

        logger.info("Verify login functionality");
        Assert.assertEquals(successfulLoginPage.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");
        Assert.assertTrue(successfulLoginPage.getPageSource().contains("Congratulations student. You successfully logged in!"));

        logger.info("Verify Logout button is displayed");
        Assert.assertTrue(successfulLoginPage.isLogoutDisplayed());
    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void NegativeLoginTest(String username, String password, String expectedErrorMessage) {
        logger.info("Starting negative login test functionality");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        logger.info("Type username:" + username);
        logger.info("Type password:" + password);
        logger.info("Click submit button");
        loginPage.executeLogin(username, password);
        logger.info("Verify error message: " + expectedErrorMessage);

        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
    }
}
