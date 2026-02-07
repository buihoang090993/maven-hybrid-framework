package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGeneration;
import pageObjects.PageGenerator;
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

    public AddEmployeePO clickToAddEmployeeButton() {
        waitForElementVisible(driver, EmployeeListUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListUI.ADD_EMPLOYEE_BUTTON);
//        return PageGenerator.getAddEmployeePage(driver);
        return PageGeneration.getPage(AddEmployeePO.class, driver);
    }
}
