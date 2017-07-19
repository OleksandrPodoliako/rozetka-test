import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Подоляко on 17.07.2017.
 */
public class PhoneCatalogPage extends AbstractPageObject {
    private By priceMinBoxLocator = By.xpath("//input[@id='price[min]']");
    private By priceMaxBoxLocator = By.xpath("//input[@id='price[max]']");
    private By submitPriceLocator = By.xpath("//button[@id = 'submitprice']");
    private By companyCheckboxLocator = By.xpath("//i[@class = 'filter-parametrs-i-l-i-default-title']");
    private By searchResultLocator = By.xpath("//div[@id = 'catalog_goods_block']");
    private By moreCompanyLocator = By.xpath("(//a[@name= 'show_more_parameters'])[1]");
    private By priceFilterLocator = By.xpath("//li[@id = 'reset_filterprice']");
    private By companyFilterLocator = By.xpath("//li[@id = 'reset_filter66']");
    private By phoneLocator = By.xpath("//div[@class = 'g-i-tile-i-title clearfix']");

    private By nameProductLabelLocator = By.xpath("//div[@class = 'g-i-tile-i-title clearfix']");
    private By priceLabelLocator = By.xpath("//div[@class = 'g-price-uah']");
    private By ratingLocator = By.xpath("//span[@class = 'sprite g-rating-stars-i']");

    private By refuseNotificationLocator = By.xpath("//div[@class = 'notificationPanelBlock']");


    public PhoneCatalogPage(WebDriver driver) {
        super(driver);
        waitTillPresent(searchResultLocator);
    }

    public PhoneCatalogPage setPrice(Integer min, Integer max) {
        getElement(priceMinBoxLocator).sendKeys(min.toString());
        getElement(priceMaxBoxLocator).sendKeys(Keys.CONTROL + "a");
        getElement(priceMaxBoxLocator).sendKeys(Keys.DELETE);
        getElement(priceMaxBoxLocator).sendKeys(max.toString());
        getElement(submitPriceLocator).click();
        waitTillPresent(priceFilterLocator);
        return new PhoneCatalogPage(driver);
    }

    public PhoneCatalogPage closeNotification() {
        getElement(refuseNotificationLocator).click();
        return new PhoneCatalogPage(driver);
    }


    public PhoneCatalogPage choseHTC() {
        getElement(moreCompanyLocator).click();
        for (WebElement choseCompanyButton : getElementList(companyCheckboxLocator)) {
            if (choseCompanyButton.getText().equals("HTC")) {
                choseCompanyButton.click();
                break;
            }
        }
        waitTillPresent(companyFilterLocator);
        return new PhoneCatalogPage(driver);
    }

    public ProductPage getPhone(int number) {
        getElementList(phoneLocator).get(number).click();
        return new ProductPage(driver);
    }

    public String getProductName(int number) {
        return getElementList(nameProductLabelLocator).get(number).getText();
    }

    public String getProductPrice(int number) {
        return getElementList(priceLabelLocator).get(number).getText();
    }

    public String getProductRating(int number) {
        return getElementList(ratingLocator).get(number).getAttribute("style");
    }

}
