import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Подоляко on 02.07.2017.
 */
public class SearchResultPage extends AbstractPageObject {
    protected By nameProductLabelLocator = By.xpath("//div[@class = 'g-i-tile-i-title clearfix']");
    protected By priceLabelLocator = By.xpath("//div[@class = 'g-price-uah']");
    protected By ratingLocator = By.xpath("//span[@class = 'sprite g-rating-stars-i']");
    protected By reviewsLocator = By.xpath("//span[@class = 'g-rating-reviews']");
    private By resultTitleLocator = By.xpath("//span[@id ='search_result_title_text']");
    private By resultLocator = By.cssSelector(".g-i-tile-i-title.clearfix>a");

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

    public String getProductName(int number) {
        return getElementList(nameProductLabelLocator).get(number).getText();
    }

    public String getProductPrice(int number) {
        return getElementList(priceLabelLocator).get(number).getText();
    }

    public String getProductRating(int number) {
        return getElementList(ratingLocator).get(number).getAttribute("style");
    }

    public String getProductReviewsCount(int number) {
        return getElementList(reviewsLocator).get(number).getText();
    }

}
