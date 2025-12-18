package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.EmployeeListUI;

public class EmployeeListPO extends BasePage {
    private WebDriver driver;

    public EmployeeListPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPIMHeaderDisplayed(WebDriver driver) {
        waitForElementVisible(driver, EmployeeListUI.PIM_HEADER);
        return isElementDisplayed(driver, EmployeeListUI.PIM_HEADER);
    }

    public void clickToAddEmployeeButton() {
        waitForElementVisible(driver, EmployeeListUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListUI.ADD_EMPLOYEE_BUTTON);
    }
}
