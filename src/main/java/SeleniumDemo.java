import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;

public class SeleniumDemo {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        findLoginPageElements(driver);
        findTestExceptionPageElements(driver);

        driver.quit();
    }

    private static void findTestExceptionPageElements(WebDriver driver){
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        WebElement javaForBeginnersLink = driver.findElement(By.linkText("Selenium WebDriver with Java for beginners program"));

        WebElement row1InputFieldTag = driver.findElement(By.tagName("input"));
        WebElement row1InputFieldClassName = driver.findElement(By.className("input-field"));
        WebElement row1InputFieldXPath = driver.findElement(By.xpath("//input[@class='input-field']"));
        WebElement row1InputFieldCss = driver.findElement(By.cssSelector("input[class='input-field']"));

        List<WebElement> buttonsList = driver.findElements(By.tagName("button"));

        WebElement editButtonId = driver.findElement(By.id("edit_btn"));
        WebElement editButtonName = driver.findElement(By.className("btn"));
        WebElement editButtonCss = driver.findElement(By.cssSelector("button[id='edit_btn']"));
        WebElement editButtonXpath = driver.findElement(By.xpath("//button[@id='edit_btn']"));

        WebElement addButtonId = driver.findElement(By.id("add_btn"));
        WebElement addButtonName = driver.findElement(By.className("btn"));
        WebElement addButtonCss = driver.findElement(By.cssSelector("button[id='add_btn']"));
        WebElement addButtonXpath = driver.findElement(By.xpath("//button[@id='add_btn']"));
    }

    private static void findLoginPageElements(WebDriver driver) {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // WebElement locators will be added here
        WebElement usernameInputField = driver.findElement(By.id("username"));
        WebElement usernameInputFieldXpath = driver.findElement(By.xpath("//input[@id='username']"));
        WebElement usernameInputFieldCss = driver.findElement(By.cssSelector("input[id='username']"));

        WebElement passwordInputField = driver.findElement(By.name("password"));
        WebElement passwordInputXpath = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement passwordInputCss = driver.findElement(By.cssSelector("input[name='password']"));

        WebElement submitButton = driver.findElement(By.className("btn"));
        WebElement submitButtonXpath = driver.findElement(By.xpath("//button[@id='submit']"));
        WebElement submitButtonCss = driver.findElement(By.cssSelector("button[id='submit']"));

        WebElement linkTextLocator = driver.findElement(By.linkText("Practice Test Automation."));
        WebElement partialLinkTexLocator = driver.findElement(By.partialLinkText("Test Automation"));

        WebElement passwordFieldBelowUsername = driver.findElement(RelativeLocator.with(By.tagName("input"))
                .below(By.id("username")));

        WebElement privacyPolicyLink = driver.findElement(RelativeLocator.with(By.tagName("a"))
                .toRightOf(By.linkText("Practice Test Automation.")));

        List<WebElement> inputFields = driver.findElements(By.tagName("input"));

        WebElement homeButton = driver.findElement(By.className("menu-item-home"));

    }

    private static String chromeTest(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        String title = driver.getTitle();
        driver.quit();
        return title;
    }

    private static String safariTest(String url) {
        WebDriver driver = new SafariDriver();
        driver.get(url);
        String title = driver.getTitle();
        driver.quit();
        return title;
    }
}
