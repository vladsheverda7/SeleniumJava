package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExceptionPage extends BasePage {

    private By addButtonLocator = By.xpath("//button[@id='add_btn']");
    private By row2InputFieldLocator = By.xpath("//div[@id='row2']/input");

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
}
