package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExceptionPage extends BasePage {

    private By addButtonLocator = By.xpath("//button[@id='add_btn']");
    private By editByttonLocator = By.xpath("//div[@id='row1']/button[@id='edit_btn']");
    private By row1InputFieldLocator = By.xpath("//div[@id='row1']/input");
    private By row2InputFieldLocator = By.xpath("//div[@id='row2']/input");
    private By save1ButtonLocator = By.xpath("//div[@id='row1']/button[@id='save_btn']");
    private By save2ButtonLocator = By.xpath("//div[@id='row2']/button[@id='save_btn']");
    private By confirmationMessageLocator = By.id("confirmation");
    private By instructionsMessageLocator = By.xpath("//p[@id='instructions']");

    public ExceptionPage(WebDriver driver) {
        super(driver);
    }

    public void visit() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions");
    }

    public void clickAddButton() {
        driver.findElement(addButtonLocator).click();
    }

    public Boolean isRow2InputFieldDisplayed() {
        return waitForElementIsDisplayed(row2InputFieldLocator);
    }

    public void enterRow2InputFieldValue(String value) {
        driver.findElement(row2InputFieldLocator).sendKeys(value);
    }

    public void enterRow1InputFieldValue(String value) {
        driver.findElement(row1InputFieldLocator).sendKeys(value);
    }

    public void clickSave2ButtonLocator() {
        driver.findElement(save2ButtonLocator).click();
    }

    public void clickSave1ButtonLocator() {
        driver.findElement(save1ButtonLocator).click();
    }

    public String getConfirmationMessage() {
        WebElement confirmationMessageElement = waitForElement(confirmationMessageLocator);
        return confirmationMessageElement.getText();
    }

    public void clearRow1InputField() {
        driver.findElement(editByttonLocator).click();
        driver.findElement(row1InputFieldLocator).clear();
    }

    public String getRow1FieldValue() {
        return getInputFieldText(row1InputFieldLocator);
    }

    public Boolean isInstructionsHidden() {
        return isElementHidden(instructionsMessageLocator);
    }
}
