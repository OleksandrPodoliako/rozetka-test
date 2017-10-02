import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

/**
 * Created by Подоляко on 02.07.2017.
 */
class SearchResultPage extends AbstractPageObject {
    private By nameProductLabelLocator = By.xpath("//div[@class = 'g-i-tile-i-title clearfix']");
    private By priceLabelLocator = By.xpath("//div[@class = 'g-price-uah']");
    private By ratingLocator = By.xpath("//span[@class = 'sprite g-rating-stars-i']");
    private By reviewsLocator = By.xpath("//span[@class = 'g-rating-reviews']");
    private By resultTitleLocator = By.xpath("//span[@id ='search_result_title_text']");
    private By resultLocator = By.cssSelector(".g-i-tile-i-title.clearfix>a");
    private By filterLabelLocator = By.xpath("//a[@class = 'filter-active-i-link novisited sprite-side']");

    SearchResultPage(WebDriver driver) {
        super(driver);
        waitTillPresent(resultLocator);
    }

    WebElement getResultTitle() {
        return getElement(resultTitleLocator);
    }

    ProductPage openProductPage(int number) {
        Reporter.log("openProductPage <br>");
        getElementList(resultLocator).get(number).click();
        return new ProductPage(driver);
    }

    String getProductName(int number) {
        return getElementList(nameProductLabelLocator).get(number).getText();
    }

    String getProductPrice(int number) {
        return getElementList(priceLabelLocator).get(number).getText();
    }

    String getProductRating(int number) {
        return getElementList(ratingLocator).get(number).getAttribute("style");
    }

    String getProductReviewsCount(int number) {
        return getElementList(reviewsLocator).get(number).getText();
    }

    WebElement getFilterLabel() {
        return getElement(filterLabelLocator);
    }

    boolean resultContainsQuery(String query) {
        boolean resultContainsQuery = false;
        List<WebElement> searchResultList = getElementList(nameProductLabelLocator);
        for (WebElement searchResult : searchResultList) {
            if (searchResult.getText().contains(query)) {
                resultContainsQuery = true;
            } else {
                resultContainsQuery = false;
            }
        }
        return resultContainsQuery;
    }

}
