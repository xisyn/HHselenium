import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends TestBase {
    @Test
    public void searchQA() {
        login();

        WebElement searchField = wd.findElement(By.xpath("//*[@data-qa='search-input']"));
        searchField.sendKeys("QA");
        searchField.submit();

        WebElement searchTitle = wd.findElement(By.xpath("//*[@data-qa='page-title']"));

        Assert.assertTrue(searchTitle.getText().contains("QA"));
    }

    @Test
    public void advancedSearch() {
        login();

        WebElement advancedSearch = wd.findElement(By.xpath("//*[@data-qa='search-input']/following-sibling::*"));
        advancedSearch.click();

        WebElement searchField = wd.findElement(By.xpath("//*[@data-qa='vacancysearch__keywords-input']"));
        searchField.sendKeys("QA");

        WebElement searchFieldTitle = wd.findElement(By.xpath("//div[text()='Ключевые слова']"));
        searchFieldTitle.click();

        WebElement spec = wd.findElement(By.xpath("//*[@name='specialization']"));
        isElementClickable(spec);
        spec.click();

        WebElement quickSearch = wd.findElement(By.xpath("(//*[@data-qa='bloko-tree-selector-popup-search'])[3]"));
        quickSearch.sendKeys("тестирование");

        WebElement categoryCheckbox = wd.findElement(By.xpath("//*[contains(text(),'Тестирование')]"));
        categoryCheckbox.click();

        WebElement submitCategory = wd.findElement(By.xpath("(//*[@data-qa='bloko-tree-selector-popup-submit'])[3]"));
        submitCategory.click();

        if (isElementVisible(By.xpath("//div[@class='region-list']/descendant::span[@data-qa='bloko-tag__text']"))) {
            WebElement deleteRegion = wd.findElement(By.xpath("//div[@class='region-list']/descendant::span[@data-qa='bloko-tag__cross']"));
            deleteRegion.click();
        }

        WebElement experienceFrom3 = wd.findElement(By.xpath("//*[@value='between3And6']/parent::label"));
        isElementClickable(experienceFrom3);
        experienceFrom3.click();

        WebElement fullEmployment = wd.findElement(By.xpath("//*[@value='full']/parent::label"));
        fullEmployment.click();

        WebElement search = wd.findElement(By.xpath("//*[@id='submit-bottom']"));
        search.click();

        /*Заголовок страницы*/
        WebElement pageTitle = wd.findElement(By.xpath("//*[@data-qa='page-title']"));
        Assert.assertTrue(pageTitle.getText().contains("QA"));
        /*Профобласть*/
        WebElement resultProfArea = wd.findElement(By.xpath("//*[@data-toggle='professionalArea']/following::span[1]"));
        Assert.assertEquals("IT, телеком", resultProfArea.getText());
        /*Специализация*/
        WebElement resultSpec = wd.findElement(By.xpath("//*[@data-toggle='specialization']/following::span[1]"));
        Assert.assertEquals("Тестирование", resultSpec.getText());
        /*Опыт работы*/
        WebElement resultExp = wd.findElement(By.xpath("//*[@data-toggle='experience']/following::span[1]"));
        Assert.assertEquals("От 3 до 6 лет", resultExp.getText());
        /*Тип занятости*/
        WebElement resultEmployment = wd.findElement(By.xpath("//*[@data-toggle='employment']/following::span[1]"));
        Assert.assertEquals("Полная занятость", resultEmployment.getText());

    }
}
