import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Подоляко on 06.06.2017.
 */
public abstract class AbstractPageObject {
    protected WebDriver driver;
    private WebDriverWait driverWait;

    AbstractPageObject(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, 10);
    }

    public WebElement getElement(By locator) {
        return driverWait
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    void waitTillDisappear(By locator) {
        driverWait
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitTillPresent(By locator) {
        driverWait
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    List<WebElement> getElementList(By locator) {
        return driverWait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
}
