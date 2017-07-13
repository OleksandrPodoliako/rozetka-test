import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Подоляко on 02.07.2017.
 */
public class SearchResultPage extends AbstractPageObject {
    private By resultTitleLocator = By.xpath("//span[@id ='search_result_title_text']");
    private By resultLocator = By.xpath("//div[@class = 'g-i-tile-i-title clearfix']");

    public SearchResultPage(WebDriver driver) {
        super(driver);
        waitTillPresent(resultLocator);
    }

    public WebElement getResultTitle() {
        return getElement(resultTitleLocator);
    }

    public ProductPage getProductPage(int number) {
        getElementList(resultLocator).get(number).click();
        return new ProductPage(driver);
    }

}
