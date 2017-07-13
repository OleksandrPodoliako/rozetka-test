import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Подоляко on 13.06.2017.
 */
public class MainPageAuthorized extends MainPageUnauthorized {
    private By searchButtonLocator = By.xpath("//span[@class='rz-search-button-go-link']");
    private By searchBoxLocator = By.xpath("(//input[@type='text'])[1]");
    private By advertisingLocator = By.xpath("//a[@name ='close']");
    private By categoriesButtonLocator = By.xpath("(//li[@name='m-main-i'])[17]");

    public MainPageAuthorized(WebDriver driver) {
        super(driver);
        waitTillPresent(userMenuLocator);
    }


    public ProfilePage openProfile() {
        getUserMenuElement().click();
        return new ProfilePage(driver);
    }

    public WebElement getSearchButton() {
        return getElement(searchButtonLocator);
    }

    public SearchResultPage searchFor(String request) {
        getElement(searchBoxLocator).sendKeys(request);
        getElement(advertisingLocator).click();
        getSearchButton().click();
        return new SearchResultPage(driver);
    }

    public CategoriesModalMenu openCategoriesMenu() {
        getCategoriesElement().click();
        return new CategoriesModalMenu(driver);
    }

    public WebElement getCategoriesElement() {
        return getElement(categoriesButtonLocator);
    }
}