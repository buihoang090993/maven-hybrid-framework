package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePO extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(css = "input[name='firstName']")
    private WebElement firstNameTextbox;

    @FindBy(css = "input[name='middleName']")
    private WebElement middleNameTextbox;

    @FindBy(css = "input[name='lastName']")
    private WebElement lastNameTextbox;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement employeeIDTextbox;

    @FindBy(xpath = "//p[text()='Create Login Details']/following-sibling::div/label/span")
    private WebElement createLoginDetailsButton;

    @FindBy(xpath = "//label[text()='Username']/parent::div/following-sibling::div/input")
    private WebElement userNameTextbox;

    @FindBy(xpath = "//label[text()='Password']/parent::div/following-sibling::div/input")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//label[text()='Confirm Password']/parent::div/following-sibling::div/input")
    private WebElement confirmPasswordTextbox;

    @FindBy(xpath = "//button[contains(string(),'Save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//p[text()='Successfully Saved']")
    private WebElement successfulSaveMessage;

    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, firstNameTextbox);
        sendKeyToElement(firstNameTextbox, firstName);
    }

    public void enterToMiddleNameTextbox(String middleName) {
        waitForElementVisible(driver, middleNameTextbox);
        sendKeyToElement(middleNameTextbox, middleName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, lastNameTextbox);
        sendKeyToElement(lastNameTextbox, lastName);
    }

    public String getEmployeeIDValue() {
        waitForElementVisible(driver, employeeIDTextbox);
        return getElementDomProperty(employeeIDTextbox, "value");
    }

    public void clickToCreateLoginDetailsCheckbox() {
        waitForElementClickable(driver, createLoginDetailsButton);
        clickToElement(createLoginDetailsButton);
    }

    public void enterToUserNameTextbox(String userName) {
        waitForElementVisible(driver, userNameTextbox);
        sendKeyToElement(userNameTextbox, userName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendKeyToElement(confirmPasswordTextbox, password);
    }

    public void clickToSaveButton() {
        waitForElementClickable(driver, saveButton);
        clickToElement(saveButton);
    }

    public boolean isSuccessfullySavedMessageDisplayed() {
        waitForElementVisible(driver, successfulSaveMessage);
        return isElementDisplayed(successfulSaveMessage);
    }
}
