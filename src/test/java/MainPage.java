import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainPage {
    ChromeDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void mainPageTest() {
        wd.get("https://hh.ru");
        WebElement menuIntem = wd.findElement(By.xpath("(//li/div[contains(@class,\"MenuItem\")])[1]"));
        WebElement loginBtn = wd.findElement(By.xpath("//div[@class='login-submit-form']/input"));

        Assert.assertEquals(menuIntem.getText(), "Ищу работу");
        Assert.assertEquals(loginBtn.getAttribute("value"), "Войти");
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
}
