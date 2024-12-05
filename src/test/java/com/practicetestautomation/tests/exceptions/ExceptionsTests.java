package com.practicetestautomation.tests.exceptions;

import com.practicetestautomation.pageobjects.ExceptionPage;
import com.practicetestautomation.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExceptionsTests extends BaseTest {
    @Test
    public void noSuchElementExceptionTest() {
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();

        Assert.assertTrue(exceptionPage.isRow2InputFieldDisplayed(), "Row 2 is not displayed");
    }

    @Test
    public void timeoutExceptionTest() {
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();

        Assert.assertTrue(exceptionPage.isRow2InputFieldDisplayed(), "Row 2 is not displayed");
    }

    @Test
    public void elementNotInteractableException() {
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();
        exceptionPage.isRow2InputFieldDisplayed();
        exceptionPage.enterRow2InputFieldValue("Sushi");
        exceptionPage.clickSave2ButtonLocator();

        Assert.assertEquals(exceptionPage.getConfirmationMessage(), "Row 2 was saved");
    }

    @Test
    public void invalidElementStateException() {
        String newValue = "Sushi";

        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clearRow1InputField();
        exceptionPage.enterRow1InputFieldValue(newValue);
        exceptionPage.clickSave1ButtonLocator();

        Assert.assertEquals(exceptionPage.getConfirmationMessage(), "Row 1 was saved");

        Assert.assertEquals(exceptionPage.getRow1FieldValue(), newValue);
    }

    @Test
    public void staleElementReferenceException() {
        ExceptionPage exceptionPage = new ExceptionPage(driver);
        exceptionPage.visit();
        exceptionPage.clickAddButton();

        Assert.assertTrue(exceptionPage.isInstructionsHidden());
    }
}
