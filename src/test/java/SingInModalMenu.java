import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Подоляко on 13.06.2017.
 */
public class SingInModalMenu extends AbstractPageObject {
    private By loginBarLocator = By.xpath("(//input[@class = 'input-text auth-input-text'])[1]");
    private By passwordBarLocator = By.xpath("(//input[@class = 'input-text auth-input-text'])[2]");
    private By singInAcceptButtonLocator = By.xpath("//button[@name='auth_submit']");

    public SingInModalMenu(WebDriver driver) {
        super(driver);
        waitTillPresent(singInAcceptButtonLocator);
    }

    public MainPageAuthorized sendData(String login, String password) {
        getElement(loginBarLocator).sendKeys(login);
        getElement(passwordBarLocator).sendKeys(password);
        getElement(singInAcceptButtonLocator).click();
        waitTillDisappear(singInAcceptButtonLocator);
        return new MainPageAuthorized(driver);

    }

}
