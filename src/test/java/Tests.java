import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
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
    private String password = "ExampleR****";
    private String request = "samsung";

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        mainPageAuthorized = new MainPageUnauthorized(driver).signIn(login, password);
    }

    @Test
    public void verifySignIn() {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifySignIn test start ");
        String userMenuAttribute = mainPageAuthorized
                .getUserMenuElement()
                .getAttribute("name");
        assertTrue(userMenuAttribute.equals("profile"));
    }

    @Test
    public void verifySignOut() {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifySignOut test start ");
        String userMenuAttribute = mainPageAuthorized
                .openProfile()
                .singOut()
                .getUserMenuElement()
                .getAttribute("name");
        assertTrue(userMenuAttribute.equals("signin"));
    }


    //write in log SubCategories
    @Test
    public void loggingSubCategories() {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "loggingSubCategories test start ");
        List<WebElement> allCategoriesList = mainPageAuthorized
                .openCategoriesMenu()
                .getAllCategoriesList();
        for (WebElement element : allCategoriesList) {
            if (element.getCssValue("Color").equals("rgb(62, 119, 170)")) {
                Reporter.log(element.getText().toString());
            }
        }
    }

    @Test
    public void verifySearchButtonIsVisible() {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifySearchButtonIsVisible test start ");
        WebElement searchButton = mainPageAuthorized
                .getSearchButton();
        assertTrue(searchButton.isDisplayed());
    }

    @Test
    public void verifySearchButtonIsInvisible() throws IOException {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifySearchButtonIsInvisible test start ");
        WebElement searchButton = mainPageAuthorized
                .getSearchButton();
        Utility.hideElement(driver, searchButton);
        assertFalse(searchButton.isDisplayed());
    }

    @Test
    public void verifyTextInResult() {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifyTextInResult test start ");
        String resultTitleText = mainPageAuthorized
                .searchFor(request)
                .getResultTitle()
                .getText();
        assertTrue(resultTitleText.contains(request));
    }

    @Test
    public void verifyColorTextInResult() {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifyColorTextInResult test start ");
        String resultTitleText = mainPageAuthorized
                .searchFor(request)
                .getResultTitle()
                .getCssValue("Color");
        assertTrue(resultTitleText.contains("rgb(0, 187, 51)"));
    }

    @Test
    public void verifyAddToMyFishes() throws IOException {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifyAddToMyFishes test start ");
        mainPageAuthorized
                .searchFor(request)
                .getProductPage(2)
                .addToWishList();
        Utility.addScreenshotToLog(driver);
    }

    @Test
    public void verifyProductName() {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifyProductName test start ");
        PhoneCatalogPage electPhonesPaga = mainPageAuthorized
                .openPhoneCatalog()
                .closeNotification()
                .setPrice(1600, 2799)
                .choseCompany("HTC");

        String phoneNameElectPage = electPhonesPaga.getProductName(0);

        ProductPage phonePage = electPhonesPaga.getPhone(0);

        String phoneNameProductPage = phonePage.getProductName();

        phoneNameElectPage = phoneNameElectPage.replace(" ", "");
        phoneNameProductPage = phoneNameProductPage.replace(" ", "");

        assertTrue(phoneNameElectPage.equals(phoneNameProductPage));
    }

    @Test
    public void verifyProductPrice() {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifyProductPrice test start ");
        PhoneCatalogPage electPhonesPaga = mainPageAuthorized
                .openPhoneCatalog()
                .closeNotification()
                .setPrice(1600, 2799)
                .choseCompany("HTC");

        String phonePriceElectPage = electPhonesPaga.getProductPrice(0);

        ProductPage phonePage = electPhonesPaga.getPhone(0);

        String phonePriceProductPage = phonePage.getProductPrice();

        phonePriceElectPage = phonePriceElectPage.replace(" ", "");
        phonePriceElectPage = phonePriceElectPage.replace(" ", "");
        phonePriceElectPage = phonePriceElectPage.replace("грн", "");
        phonePriceProductPage = phonePriceProductPage.replace(" ", "");

        assertTrue(phonePriceElectPage.equals(phonePriceProductPage));
    }

    @Test
    public void verifyProductRating() {
        Date timeTestBegin = new Date();
        Reporter.log("[" + timeTestBegin.toString() + "]" + "verifyProductRating test start ");
        PhoneCatalogPage electPhonesPaga = mainPageAuthorized
                .openPhoneCatalog()
                .closeNotification()
                .setPrice(1600, 2799)
                .choseCompany("HTC");

        String phoneRatingElectPage = electPhonesPaga.getProductRating(0);

        ProductPage phonePage = electPhonesPaga.getPhone(0);

        String phoneRatingProductPage = phonePage.getProductRating();

        assertTrue(phoneRatingElectPage.equals(phoneRatingProductPage));
    }

    @AfterMethod
    public void close() {
        driver.close();
        driver.quit();
    }

}
