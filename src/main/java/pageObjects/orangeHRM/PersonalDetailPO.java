package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.PersonalDetailUI;

public class PersonalDetailPO extends BasePage {
    private WebDriver driver;

    public PersonalDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailUI.FIRST_NAME_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getMiddleNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailUI.MIDDLE_NAME_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailUI.MIDDLE_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailUI.LAST_NAME_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmployeeIDTextboxValue() {
        waitForElementVisible(driver, PersonalDetailUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDomProperty(driver, PersonalDetailUI.EMPLOYEE_ID_TEXTBOX, "value");
    }

}
