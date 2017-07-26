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
    private By nameProductLabelLocator = By.xpath("//h1[@class = 'detail-title']");
    private By priceLabelLocator = By.xpath("//span[@id = 'price_label']");
    private By ratingLocator = By.xpath("//span[@class = 'g-rating-stars-i-medium']");
    private By reviewsLocator = By.xpath("//a[@class = 'novisited g-rating-reviews-link-medium']");


    public ProductPage(WebDriver driver) {
        super(driver);
        waitTillPresent(imageLocator);
    }

    public void addToWishList() {
        getElement(addToWishButtonLocator).click();
        getElement(saveInWishListButtonLocator).click();
        waitTillPresent(imageInWishListLocator);
    }

    public String getProductName() {
        return getElement(nameProductLabelLocator).getText();
    }

    public String getProductPrice() {
        return getElement(priceLabelLocator).getText();
    }

    public String getProductRating() {
        return getElement(ratingLocator).getAttribute("style");
    }

    public String getProductReviewsCount() {
        return getElement(reviewsLocator).getText();
    }

    public ReviewsPage openReviews(){
        getElement(reviewsLocator).click();
        return new ReviewsPage(driver);
    }
}
