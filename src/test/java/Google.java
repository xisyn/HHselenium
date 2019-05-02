import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Google {
    ChromeDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void searchHH() {
        wd.get("https://google.com");
        WebElement searchField = wd.findElement(By.xpath("//div/input[@title=\"Поиск\"]"));
        searchField.sendKeys("HH");
        searchField.submit();
        WebElement firstResult = wd.findElement(By.xpath("(//*[@class=\"r\"]/a)[1]"));
        Assert.assertTrue(firstResult.getText().contains("HeadHunter"));
        System.out.println(firstResult.getText());
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
}
