package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGeneration;
import pageUIs.orangeHRM.PersonalDetailsUI;

public class PersonalDetailsPO extends BasePage {
    private WebDriver driver;

    public PersonalDetailsPO(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsUI.FIRST_NAME_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailsUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getMiddleNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsUI.MIDDLE_NAME_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailsUI.MIDDLE_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsUI.LAST_NAME_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailsUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmployeeIDTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailsUI.EMPLOYEE_ID_TEXTBOX, "value");
    }

    public EmployeeListPO clickToEmployeeListButton() {
        waitForElementClickable(driver, PersonalDetailsUI.EMPLOYEE_LIST_BUTTON);
        clickToElement(driver, PersonalDetailsUI.EMPLOYEE_LIST_BUTTON);
//        return PageGenerator.getEmployeeListPage(driver);
        return PageGeneration.getPage(EmployeeListPO.class, driver);
    }

}
