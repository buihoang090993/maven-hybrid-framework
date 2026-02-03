package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.AddEmployeeUI;

public class AddEmployeePO extends BasePage {
    private WebDriver driver;

    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, AddEmployeeUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, AddEmployeeUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToMiddleNameTextbox(String middleName) {
        waitForElementVisible(driver, AddEmployeeUI.MIDDLE_NAME_TEXTBOX);
        sendKeyToElement(driver, AddEmployeeUI.MIDDLE_NAME_TEXTBOX, middleName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, AddEmployeeUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, AddEmployeeUI.LAST_NAME_TEXTBOX, lastName);
    }

    public String getEmployeeIDValue() {
        // Lấy ra value trong HTML -> chỉ cần dùng wait presence
        waitForElementPresence(driver, AddEmployeeUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDomProperty(driver, AddEmployeeUI.EMPLOYEE_ID_TEXTBOX, "value");
    }

    public void clickToCreateLoginDetailsCheckbox() {
        waitForElementClickable(driver, AddEmployeeUI.CREATE_LOGIN_DETAILS_BUTTON);
        clickToElement(driver, AddEmployeeUI.CREATE_LOGIN_DETAILS_BUTTON);
    }

    public void enterToUserNameTextbox(String userName) {
        waitForElementVisible(driver, AddEmployeeUI.USER_NAME_TEXTBOX);
        sendKeyToElement(driver, AddEmployeeUI.USER_NAME_TEXTBOX, userName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, AddEmployeeUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AddEmployeeUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, AddEmployeeUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AddEmployeeUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public void clickToSaveButton() {
        waitForElementClickable(driver, AddEmployeeUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeeUI.SAVE_BUTTON);
    }

    public boolean isSuccessfullySavedMessageDisplayed() {
        waitForElementVisible(driver, AddEmployeeUI.SUCCESSFUL_SAVE_MESSAGE);
        return isElementDisplayed(driver, AddEmployeeUI.SUCCESSFUL_SAVE_MESSAGE);
    }
}
