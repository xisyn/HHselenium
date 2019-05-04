import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    ChromeDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().window().maximize();
    }

    @Test
    public void mainPageTest() {
        wd.get("https://hh.ru");
        WebElement menuIntem = wd.findElement(By.xpath("(//li/div[contains(@class,\"MenuItem\")])[1]"));
        WebElement loginBtn = wd.findElement(By.xpath("//div[@class='login-submit-form']/input"));

        Assert.assertEquals(menuIntem.getText(), "Ищу работу");
        Assert.assertEquals(loginBtn.getAttribute("value"), "Войти");
    }

    @Test
    public void login() throws IOException {
        wd.get("https://hh.ru");
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream("src/test/resources/config.propeties");
        properties.load(fileInputStream);

        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        WebElement closeRegion = wd.findElement(By.xpath("//div[@data-qa=\"notification-close-button\"]"));
        closeRegion.click();

        WebElement loginField = wd.findElement(By.xpath("//*[@class='login-input']/input[@data-qa='login-input-username']"));
        WebElement passwordField = wd.findElement(By.xpath("//*[@class='login-input']/input[@data-qa='login-input-password']"));

        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        passwordField.submit();

        WebElement profileMenu = wd.findElement(By.xpath("//span[@class='navi-item__employer-info']"));
        Assert.assertEquals(profileMenu.getText(), "Дмитрий Хицунов");
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
}
