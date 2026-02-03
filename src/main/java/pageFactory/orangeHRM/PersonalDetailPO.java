package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalDetailPO extends BasePageFactory {
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

    public PersonalDetailPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, firstNameTextbox);
        return getElementDomProperty(firstNameTextbox, "value");
    }

    public String getMiddleNameTextboxValue() {
        waitForElementVisible(driver, middleNameTextbox);
        return getElementDomProperty(middleNameTextbox, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, lastNameTextbox);
        return getElementDomProperty(lastNameTextbox, "value");
    }

    public String getEmployeeIDTextboxValue() {
        waitForElementVisible(driver, employeeIDTextbox);
        return getElementDomProperty(employeeIDTextbox, "value");
    }

}
