import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SeleniumDemo {
    public static void main(String[] args){
        String chromeTitle = chromeTest("https://www.google.com/");
        System.out.println(chromeTitle);

        String safariTitle = safariTest("https://www.google.com/");
        System.out.println(safariTitle);
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
