import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * Created by Подоляко on 14.06.2017.
 */
public class ProfilePage extends AbstractPageObject {
    private By exitButtonLocator = By.xpath("//a[@id = 'profile_signout']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public MainPageUnauthorized singOut() {
        Reporter.log("singOut <br>");
        getElement(exitButtonLocator).click();
        return new MainPageUnauthorized(driver);
    }
}
