package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.DashboardUI;

public class DashboardPO extends BasePage {
    private WebDriver driver;

    public DashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardHeaderDisplayed(WebDriver driver) {
        waitForElementVisible(driver, DashboardUI.DASHBOARD_HEADER);
        return isElementDisplayed(driver, DashboardUI.DASHBOARD_HEADER);
    }

    public void clickToPIMModule() {
        waitForElementClickable(driver, DashboardUI.PIM_MODULE);
        clickToElement(driver, DashboardUI.PIM_MODULE);
    }
}
