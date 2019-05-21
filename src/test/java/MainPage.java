import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPage extends TestBase {
    @Test
    public void mainPageTest() {
        WebElement menuIntem = wd.findElement(By.xpath("(//li/div[contains(@class,\"MenuItem\")])[1]"));
        WebElement loginBtn = wd.findElement(By.xpath("//div[@class='login-submit-form']/input"));

        Assert.assertEquals(menuIntem.getText(), "Ищу работу");
        Assert.assertEquals(loginBtn.getAttribute("value"), "Войти");
    }

    @Test
    public void testLogin() {
        login();
        WebElement menuItem = wd.findElement(By.xpath("//*[@data-qa='mainmenu_myResumes']"));
        Assert.assertEquals(menuItem.getText(), "Мои резюме");
    }

}
