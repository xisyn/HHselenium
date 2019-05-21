import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    ChromeDriver wd;
    private Properties properties;

    public TestBase() {
        properties = new Properties();
    }

    @BeforeSuite
    public void init() throws IOException {
        properties.load(new FileInputStream("src/test/resources/config.propeties"));

        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().window().maximize();
    }

    @AfterSuite
    public void stop() {
        wd.quit();
    }

    public void goToMainPage() {
        wd.get("https://hh.ru");
    }

    public void login() {
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        goToMainPage();

        WebElement closeRegion = wd.findElement(By.xpath("//div[@data-qa=\"notification-close-button\"]"));
        closeRegion.click();

        WebElement loginField = wd.findElement(By.xpath("//*[@class='login-input']/input[@data-qa='login-input-username']"));
        WebElement passwordField = wd.findElement(By.xpath("//*[@class='login-input']/input[@data-qa='login-input-password']"));

        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        passwordField.submit();
    }
}
