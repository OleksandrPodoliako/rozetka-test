import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Подоляко on 28.07.2017.
 */
class AddInWishDialogBox extends AbstractPageObject {
    private By allBoxLocator = By.xpath("//div[@class = 'popup-css popup-wishlists']");

    AddInWishDialogBox(WebDriver driver) {
        super(driver);
        waitTillPresent(allBoxLocator);
    }

    WebElement getDialogBox() {
        return getElement(allBoxLocator);
    }
}
