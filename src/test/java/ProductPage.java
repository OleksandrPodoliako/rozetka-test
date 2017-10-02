import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * Created by Подоляко on 03.07.2017.
 */
class ProductPage extends AbstractPageObject {
    private By addToWishButtonLocator = By.xpath("//a[@name = 'towishlist']");
    private By imageLocator = By.xpath("//div[@class = 'responsive-img']");
    private By saveInWishListButtonLocator = By.xpath("//button[@class = 'btn-link-i']");
    private By nameProductLabelLocator = By.xpath("//h1[@class = 'detail-title']");
    private By priceLabelLocator = By.xpath("//span[@id = 'price_label']");
    private By ratingLocator = By.xpath("//span[@class = 'g-rating-stars-i-medium']");
    private By reviewsLocator = By.xpath("//a[@class = 'novisited g-rating-reviews-link-medium']");


    ProductPage(WebDriver driver) {
        super(driver);
        waitTillPresent(imageLocator);
    }

    AddInWishDialogBox addToWishList() {
        Reporter.log("addToWishList <br>");
        getElement(addToWishButtonLocator).click();
        getElement(saveInWishListButtonLocator).click();
        return new AddInWishDialogBox(driver);
    }

    String getProductName() {
        return getElement(nameProductLabelLocator).getText();
    }

    String getProductPrice() {
        return getElement(priceLabelLocator).getText();
    }

    String getProductRating() {
        return getElement(ratingLocator).getAttribute("style");
    }

    String getProductReviewsCount() {
        return getElement(reviewsLocator).getText();
    }

    ReviewsPage openReviews() {
        Reporter.log("openReviews <br>");
        getElement(reviewsLocator).click();
        return new ReviewsPage(driver);
    }
}
