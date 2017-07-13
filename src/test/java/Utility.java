import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Подоляко on 06.06.2017.
 */
public final class Utility {
    public static void doScreenshot(WebDriver driver) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "c:\\" + screenshot.getName();
        FileUtils.copyFile(screenshot, new File(path));
    }

    public static void hideElement(WebDriver driver, WebElement webElement) throws IOException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='hidden'", webElement);
    }

    public static void changeColor(WebDriver driver, WebElement webElement) throws IOException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color='red'", webElement);
    }
}