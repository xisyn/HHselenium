import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTests extends TestBase {
    @Test
    public void searchQA() throws IOException {
        login();

        WebElement searchField = wd.findElement(By.xpath("//*[@data-qa='search-input']"));
        searchField.sendKeys("QA");
        searchField.submit();

        WebElement searchTitle = wd.findElement(By.xpath("//*[@data-qa='page-title']"));

        Assert.assertTrue(searchTitle.getText().contains("QA"));
    }
}
