import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Подоляко on 06.06.2017.
 */
public final class Utility {
    public static void addScreenshotToLog(WebDriver driver) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "c:\\" + screenshot.getName();
        FileUtils.copyFile(screenshot, new File(path));
        Reporter.log("<img src=\"file:///" + path + "\" alt=\"\"/><br />");
    }

    public static void hideElement(WebDriver driver, WebElement webElement) throws IOException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='hidden'", webElement);
    }

    public static void changeColor(WebDriver driver, WebElement webElement) throws IOException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color='red'", webElement);
    }

    public static int getNumbersFromText(String text) {
        int numbers;
        StringBuilder onlyNumbers = new StringBuilder();
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            onlyNumbers.append(matcher.group());
        }
        text = onlyNumbers.toString();
        numbers = Integer.parseInt(text);
        return numbers;
    }
}