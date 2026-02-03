package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DashboardPO extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimModule;

    public DashboardPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardHeaderDisplayed(WebDriver driver) {
        waitForElementVisible(driver, dashboardHeader);
        return isElementDisplayed(dashboardHeader);
    }

    public void clickToPIMModule() {
        waitForElementClickable(driver, pimModule);
        clickToElement(pimModule);
    }
}
