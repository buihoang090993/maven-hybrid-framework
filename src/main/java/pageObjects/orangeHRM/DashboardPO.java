package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v139.page.Page;
import pageObjects.PageGeneration;
import pageObjects.PageGenerator;
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

    public EmployeeListPO clickToPIMModule() {
        waitForElementClickable(driver, DashboardUI.PIM_MODULE);
        clickToElement(driver, DashboardUI.PIM_MODULE);
//        return PageGenerator.getEmployeeListPage(driver);
        return PageGeneration.getPage(EmployeeListPO.class, driver);
    }
}
