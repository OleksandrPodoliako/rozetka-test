import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Подоляко on 26.07.2017.
 */
public class ReviewsPage extends AbstractPageObject{
    private By reviewsLocator = By.xpath(".pp-review-inner");
    private By reviewsMainPanelLocator = By.cssSelector("#reviews_container_parent");
    public ReviewsPage(WebDriver driver) {
        super(driver);
        waitTillPresent(reviewsMainPanelLocator);
    }

    public WebElement getReviewsMainPanel(){
        return getElement(reviewsMainPanelLocator);
    }

    public List<WebElement> getReviewsList(){
        return getElementList(reviewsLocator);
    }
}
