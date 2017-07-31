import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by Подоляко on 06.06.2017.
 */
public class Tests {
    private WebDriver driver;
    private MainPageAuthorized mainPageAuthorized;
    private String login = "emailForExample@ukr.net";
    private String password = "ExampleR2010";
    private String searchQuery = "samsung";
    private String otherSearchQuery = "IPAD 32GB";

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        mainPageAuthorized = new MainPageUnauthorized(driver).signIn(login, password);
    }

    @Test
    public void verifySignIn() {
        String userMenuAttribute = mainPageAuthorized
                .getUserMenuElement()
                .getAttribute("name");
        assertTrue(userMenuAttribute.equals("profile"));
    }

    @Test
    public void verifySignOut() {
        String userMenuAttribute = mainPageAuthorized
                .openProfile()
                .singOut()
                .getUserMenuElement()
                .getAttribute("name");
        assertTrue(userMenuAttribute.equals("signin"));
    }

    @Test
    public void loggingSubCategories() {
        int numberOfSubCategories = 0;
        List<WebElement> allCategoriesList = mainPageAuthorized
                .openCategoriesMenu()
                .getAllCategoriesList();
        for (WebElement element : allCategoriesList) {
            if (element.getCssValue("Color").equals("rgb(62, 119, 170)")) {
                Reporter.log(element.getText().toString() + "<br>");
                numberOfSubCategories++;
            }
        }
        assertTrue(numberOfSubCategories > 0);
    }

    @Test
    public void verifySearchButtonIsVisible() {
        WebElement searchButton = mainPageAuthorized
                .getSearchButton();
        assertTrue(searchButton.isDisplayed());
    }

    @Test
    public void verifySearchButtonIsInvisible() throws IOException {
        WebElement searchButton = mainPageAuthorized
                .getSearchButton();
        Utility.hideElement(driver, searchButton);
        assertFalse(searchButton.isDisplayed());
    }

    @Test
    public void verifyTextInResult() {
        String resultTitleText = mainPageAuthorized
                .searchFor(searchQuery)
                .getResultTitle()
                .getText();
        assertTrue(resultTitleText.contains(searchQuery));
    }

    @Test
    public void verifyColorTextInResult() {
        String resultTitleText = mainPageAuthorized
                .searchFor(searchQuery)
                .getResultTitle()
                .getCssValue("Color");
        assertTrue(resultTitleText.contains("rgb(0, 187, 51)"));
    }

    @Test
    public void verifyAddToMyFishes() throws IOException {
        WebElement inWishDialogDox = mainPageAuthorized
                .searchFor(searchQuery)
                .openProductPage(2)
                .addToWishList()
                .getDialogBox();
        Utility.addScreenshotToLog(driver);
        assertTrue(inWishDialogDox.isDisplayed());
    }

    @Test
    public void verifyProductName() {
        PhoneCatalogPage phoneCatalogPaga = mainPageAuthorized
                .openPhoneCatalog()
                .closeNotification()
                .setPrice(1600, 2799)
                .choseCompany("HTC");

        String phoneName = phoneCatalogPaga.getProductName(0);

        ProductPage phonePage = phoneCatalogPaga.getPhone(0);

        String otherPhoneName = phonePage.getProductName();

        phoneName = phoneName.replace(" ", "");
        otherPhoneName = otherPhoneName.replace(" ", "");

        assertTrue(phoneName.equals(otherPhoneName));
    }

    @Test
    public void verifyProductPrice() {
        PhoneCatalogPage phoneCatalogPaga = mainPageAuthorized
                .openPhoneCatalog()
                .closeNotification()
                .setPrice(1600, 2799)
                .choseCompany("HTC");

        String phonePrice = phoneCatalogPaga.getProductPrice(0);

        ProductPage phonePage = phoneCatalogPaga.getPhone(0);

        String otherPhonePrice = phonePage.getProductPrice();

        phonePrice = phonePrice.replace(" ", "");
        phonePrice = phonePrice.replace(" ", "");
        phonePrice = phonePrice.replace("грн", "");
        otherPhonePrice = otherPhonePrice.replace(" ", "");

        assertTrue(phonePrice.equals(otherPhonePrice));
    }

    @Test
    public void verifyProductRating() {
        PhoneCatalogPage phoneCatalogPaga = mainPageAuthorized
                .openPhoneCatalog()
                .closeNotification()
                .setPrice(1600, 2799)
                .choseCompany("HTC");

        String phoneRating = phoneCatalogPaga.getProductRating(0);

        ProductPage phonePage = phoneCatalogPaga.getPhone(0);

        String otherPhoneRating = phonePage.getProductRating();

        assertTrue(phoneRating.equals(otherPhoneRating));
    }

    @Test
    public void verifyTitleContains32Gb() {
        String resultTitleText = mainPageAuthorized
                .searchFor(otherSearchQuery)
                .getResultTitle()
                .getText();
        assertTrue(resultTitleText.contains("32GB"));
    }

    @Test
    public void verifyProductReviews() {
        //number of reviews on Search Page
        SearchResultPage ipadResultPage = mainPageAuthorized
                .searchFor(otherSearchQuery);
        String numberOfReviewsText = ipadResultPage
                .getProductReviewsCount(1);
        int numberOfReviews = Utility.getNumbersFromText(numberOfReviewsText);

        //number of reviews on Product Page
        String otherNumberOfReviewsText = ipadResultPage
                .openProductPage(1)
                .getProductReviewsCount();
        int otherNumberOfReviews = Utility.getNumbersFromText(otherNumberOfReviewsText);
        assertTrue(numberOfReviews == otherNumberOfReviews);

    }

    @Test
    public void verifyReviewsPageIsVisible() {
        WebElement reviewsMainPanel = mainPageAuthorized
                .searchFor(otherSearchQuery)
                .openProductPage(1)
                .openReviews()
                .getReviewsMainPanel();
        assertTrue(reviewsMainPanel.isDisplayed());
    }

    @AfterMethod
    public void close() {
        driver.close();
        driver.quit();
    }

}
