import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Подоляко on 03.07.2017.
 */
public class ProductPage extends AbstractPageObject {
    private By addToWishButtonLocator = By.xpath("//a[@name = 'towishlist']");
    private By imageLocator = By.xpath("//div[@class = 'responsive-img']");
    private By saveInWishListButtonLocator = By.xpath("//button[@class = 'btn-link-i']");
    private By imageInWishListLocator = By.xpath("//img[@class = 'g-img-icon']");

    public ProductPage(WebDriver driver) {
        super(driver);
        waitTillPresent(imageLocator);
    }

    public void addToWishList() {
        getElement(addToWishButtonLocator).click();
        getElement(saveInWishListButtonLocator).click();
        waitTillPresent(imageInWishListLocator);
    }
}
