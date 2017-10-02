import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

/**
 * Created by Подоляко on 13.06.2017.
 */
class MainPageAuthorized extends MainPageUnauthorized {
    private By searchButtonLocator = By.xpath("//span[@class='rz-search-button-go-link']");
    private By searchBoxLocator = By.xpath("(//input[@type='text'])[1]");
    private By advertisingLocator = By.xpath("//a[@name ='close']");
    private By categoriesButtonLocator = By.xpath("(//li[@name='m-main-i'])[17]");
    private By toPhoneCatalogButtonLocator = By.xpath("(//span[@class = 'popular-categories-i-img'])[2]");
    private By categoryComboBoxLocator = By.xpath("//span[@name='rz-search-category-value']");
    private By categoryFromComboBoxLocator = By.xpath("//a[@class='rz-header-search-category-l-i-link sprite-side novisited']");

    MainPageAuthorized(WebDriver driver) {
        super(driver);
        waitTillPresent(userMenuLocator);
    }


    ProfilePage openProfile() {
        Reporter.log("openProfile <br>");
        getUserMenuElement().click();
        return new ProfilePage(driver);
    }

    WebElement getSearchButton() {
        return getElement(searchButtonLocator);
    }

    SearchResultPage searchFor(String searchQuery) {
        getElement(searchBoxLocator).sendKeys(searchQuery);
        getElement(advertisingLocator).click();
        getSearchButton().click();
        return new SearchResultPage(driver);
    }

    SearchResultPage searchFor(String searchQuery,String Category) {
        getElement(searchBoxLocator).sendKeys(searchQuery);
        getElement(advertisingLocator).click();
        getElement(categoryComboBoxLocator).click();
        List<WebElement> categoryList =  getElementList(categoryFromComboBoxLocator);
        for (WebElement categoryButton:categoryList){
            if (categoryButton.getText().contains(Category)){
                categoryButton.click();
            }
        }
        getSearchButton().click();
        return new SearchResultPage(driver);
    }

    CategoriesModalMenu openCategoriesMenu() {
        Reporter.log("openCategoriesMenu <br>");
        getCategoriesElement().click();
        return new CategoriesModalMenu(driver);
    }

    PhoneCatalogPage openPhoneCatalog() {
        Reporter.log("openPhoneCatalog <br>");
        getElement(toPhoneCatalogButtonLocator).click();
        return new PhoneCatalogPage(driver);
    }

    WebElement getCategoriesElement() {
        return getElement(categoriesButtonLocator);
    }
}
