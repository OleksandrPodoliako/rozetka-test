import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * Created by Подоляко on 06.06.2017.
 */
class MainPageUnauthorized extends AbstractPageObject {
    By userMenuLocator = By.xpath("//span[@name='splash-button']/a");


    MainPageUnauthorized(WebDriver driver) {
        super(driver);
        driver.get("https://www.rozetka.com.ua/");
        waitTillPresent(userMenuLocator);
    }

    MainPageAuthorized signIn(String login, String password) {
        Reporter.log("signIn <br>");
        return openSignInMenu().sendData(login, password);
    }

    SignInModalMenu openSignInMenu() {
        getUserMenuElement().click();
        return new SignInModalMenu(driver);
    }

    WebElement getUserMenuElement() {
        return getElement(userMenuLocator);
    }


}
