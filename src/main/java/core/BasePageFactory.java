package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePageFactory {

    public static BasePageFactory getBasePage() {
        return new BasePageFactory();
    }

    private final  Duration SHORT_TIMEOUT = Duration.ofSeconds(10);
    private final  Duration LONG_TIMEOUT = Duration.ofSeconds(30);

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clickToElement(WebElement element) {
        element.click();
    }

    public void sendKeyToElement(WebElement element, String valueToSend) {
        element.clear();
        element.sendKeys(valueToSend);
    }

    public String getElementDomProperty(WebElement element, String propertyName) {
        return element.getDomProperty(propertyName);
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }


    public WebElement waitForElementVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitForListElementInvisible(WebDriver driver, List<WebElement> elements) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public WebElement waitForElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isLoadingIconDisappear(WebDriver driver) {
        return waitForListElementInvisible(driver, driver.findElements(By.xpath("//div[contains(@class,'oxd-loading-spinner')]")));
    }
}
