import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
    private final static Logger logger = Logger.getLogger(Tests.class);
    private WebDriver driver;
    private MainPageAuthorized mainPageAuthorized;
    private String login = "emailForExample@ukr.net";
    private String password = "ExampleR****";
    private String request = "samsung";

    @BeforeClass
    public void setUpLogger() {
        PropertyConfigurator.configure("src/log4j.properties");
    }

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        mainPageAuthorized = new MainPageUnauthorized(driver).signIn(login, password);
    }

    @Test
    public void verifySignIn() {
        logger.info("verifySignIn test start");
        String userMenuAttribute = mainPageAuthorized
                .getUserMenuElement()
                .getAttribute("name");
        assertTrue(userMenuAttribute.equals("profile"));
    }

    @Test
    public void verifySignOut() {
        logger.info("verifySignOut test start");
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
        logger.info("loggingSubCategories test start");
        List<WebElement> allCategoriesList = mainPageAuthorized
                .openCategoriesMenu()
                .getAllCategoriesList();
        for (WebElement element : allCategoriesList) {
            if (element.getCssValue("Color").equals("rgb(62, 119, 170)")) {
                logger.info(element.getText());
            }
        }
    }

    @Test
    public void verifySearchButtonIsVisible() {
        logger.info("verifySearchButtonIsVisible");
        WebElement searchButton = mainPageAuthorized
                .getSearchButton();
        assertTrue(searchButton.isDisplayed());
    }

    @Test
    public void verifySearchButtonIsInvisible() throws IOException {
        logger.info("verifySearchButtonIsInvisible");
        WebElement searchButton = mainPageAuthorized
                .getSearchButton();
        Utility.hideElement(driver, searchButton);
        assertFalse(searchButton.isDisplayed());
    }

    @Test
    public void verifyTextInResult() {
        logger.info("verifyTextInResult");
        String resultTitleText = mainPageAuthorized
                .searchFor(request)
                .getResultTitle()
                .getText();
        assertTrue(resultTitleText.contains(request));
    }

    @Test
    public void verifyColorTextInResult() {
        logger.info("verifyColorTextInResult");
        String resultTitleText = mainPageAuthorized
                .searchFor(request)
                .getResultTitle()
                .getCssValue("Color");
        assertTrue(resultTitleText.contains("rgb(0, 187, 51)"));
    }

    @Test
    public void verifyAddToMyFishes() throws InterruptedException, IOException {
        logger.info("verifyAddToMyFishes");
        mainPageAuthorized
                .searchFor(request)
                .getProductPage(2)
                .addToWishList();
        Utility.doScreenshot(driver);
    }

    @AfterMethod
    public void close() {
        driver.close();
        driver.quit();
    }

}
