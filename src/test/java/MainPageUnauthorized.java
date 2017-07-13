import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Подоляко on 06.06.2017.
 */
public class MainPageUnauthorized extends AbstractPageObject {
    protected By userMenuLocator = By.xpath("//span[@name='splash-button']/a");


    public MainPageUnauthorized(WebDriver driver) {
        super(driver);
        driver.get("https://www.rozetka.com.ua/");
        waitTillPresent(userMenuLocator);
    }

    public MainPageAuthorized signIn(String login, String password) {
        return openSignInMenu().sendData(login, password);
    }

    public SingInModalMenu openSignInMenu() {
        getUserMenuElement().click();
        return new SingInModalMenu(driver);
    }

    public WebElement getUserMenuElement() {
        return getElement(userMenuLocator);
    }


}
