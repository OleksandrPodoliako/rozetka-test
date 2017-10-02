import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Подоляко on 26.06.2017.
 */
class CategoriesModalMenu extends AbstractPageObject {
    private By allCategoriesLocator = By.xpath("//div[@class = 'all-cat-b-content']//a");

    CategoriesModalMenu(WebDriver driver) {
        super(driver);
        waitTillPresent(allCategoriesLocator);
    }

    List<WebElement> getAllCategoriesList() {
        return getElementList(allCategoriesLocator);
    }
}
