package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGeneration;
import pageObjects.orangeHRM.*;
import pageUIs.orangeHRM.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BasePage {

    public static BasePage getBasePage() {
        return new BasePage();
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

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    private List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, SHORT_TIMEOUT).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, String keyToSend) {
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public void getAlertText(WebDriver driver) {
        waitAlertPresence(driver).getText();
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String id : allWindows) {
            if(!id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }

        sleepInSecond(2);
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedPageTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String id : allWindows) {
            driver.switchTo().window(id);
            sleepInSecond(2);
            if(Objects.requireNonNull(driver.getTitle()).contains(expectedPageTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String id : allWindows) {
            if(!id.equals(windowID)) {
                driver.switchTo().window(id);
                sleepInSecond(2);
                driver.close();
            }
        }

        driver.switchTo().window(windowID);
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String valueToSend) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(valueToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public String getSelectItemInDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemEditableDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
        sendKeyToElement(driver, parentLocator, expectedItem);
        sleepInSecond(2); //-- Comment đi thì không click được
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

        for (WebElement item : allItems) {
            if(item.getText().equals(expectedItem)){
                item.click();
                sleepInSecond(2);
                break;
            }
        }
    }

    public void selectItemInSelectableDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) throws InterruptedException {
        clickToElement(driver, parentLocator);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));
        for(WebElement item : allItems) {
            if(item.getText().equals(expectedItem)) {
                item.click();
                sleepInSecond(2);
                break;
            }
        }
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getElementDomAttribute(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDomProperty(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementCss(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementSize(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator) {
        if(!isElementSelected(driver, locator)) {
            clickToElement(driver, locator);
        }
    }

    public void unCheckToCheckboxRadio(WebDriver driver, String locator) {
        if(isElementSelected(driver, locator)) {
            clickToElement(driver, locator);
        }
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver, String locator) {
        driver.switchTo().defaultContent();
    }

    public void leftClickToElement(WebDriver driver, String locator) {
        new Actions(driver).click(getElement(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    public void dragAndDropToElement(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }

    public void hightlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, element.getAttribute("style"));
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];").equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void sendKeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public WebElement waitForElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitForListElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitForElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public boolean waitForListElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    public WebElement waitForElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitForListElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitForElementSelected(WebDriver driver, String locator) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver, String locator) {
        return new WebDriverWait(driver, LONG_TIMEOUT).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public boolean isLoadingIconDisappear(WebDriver driver) {
        return waitForListElementInvisible(driver, "//div[contains(@class,'oxd-loading-spinner')]");
    }

    public PersonalDetailsPO openPersonalDetailsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.PERSONAL_DETAILS_LINK);
        clickToElement(driver, BasePageUI.PERSONAL_DETAILS_LINK);
        return PageGeneration.getPage(PersonalDetailsPO.class, driver);
    }

    public ContactDetailsPO openContactDetailsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.CONTACT_DETAILS_LINK);
        clickToElement(driver, BasePageUI.CONTACT_DETAILS_LINK);
        return PageGeneration.getPage(ContactDetailsPO.class, driver);
    }

    public EmergencyContactsPO openEmergencyContactsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.EMERGENCY_CONTACTS_LINK);
        clickToElement(driver, BasePageUI.EMERGENCY_CONTACTS_LINK);
        return PageGeneration.getPage(EmergencyContactsPO.class, driver);
    }

    public DependentsPO openDependentsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.DEPENDENTS_LINK);
        clickToElement(driver, BasePageUI.DEPENDENTS_LINK);
        return PageGeneration.getPage(DependentsPO.class, driver);
    }

    public ImmigrationPO openImmigrationPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.IMMIGRATION_LINK);
        clickToElement(driver, BasePageUI.IMMIGRATION_LINK);
        return PageGeneration.getPage(ImmigrationPO.class, driver);
    }

    public JobPO openJobPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.JOB_LINK);
        clickToElement(driver, BasePageUI.JOB_LINK);
        return PageGeneration.getPage(JobPO.class, driver);
    }

    public SalaryPO openSalaryPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.SALARY_LINK);
        clickToElement(driver, BasePageUI.SALARY_LINK);
        return PageGeneration.getPage(SalaryPO.class, driver);
    }

    public ReportToPO openReportToPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.REPORT_TO_LINK);
        clickToElement(driver, BasePageUI.REPORT_TO_LINK);
        return PageGeneration.getPage(ReportToPO.class, driver);
    }

    public QualificationsPO openQualificationsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.QUALIFICATIONS_LINK);
        clickToElement(driver, BasePageUI.QUALIFICATIONS_LINK);
        return PageGeneration.getPage(QualificationsPO.class, driver);
    }

    public MembershipsPO openMembershipsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MEMBERSHIPS_LINK);
        clickToElement(driver, BasePageUI.MEMBERSHIPS_LINK);
        return PageGeneration.getPage(MembershipsPO.class, driver);
    }
}
