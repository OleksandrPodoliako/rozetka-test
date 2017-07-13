import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Подоляко on 26.06.2017.
 */
public class CategoriesModalMenu extends AbstractPageObject {
    private By allCategoriesLocator = By.xpath("//div[@class = 'all-cat-b-content']//a");

    public CategoriesModalMenu(WebDriver driver) {
        super(driver);
        waitTillPresent(allCategoriesLocator);
    }

    public List<WebElement> getAllCategoriesList() {
        return getElementList(allCategoriesLocator);
    }
}
