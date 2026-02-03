package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeListPO extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//h6[text()='PIM']")
    private WebElement pimHeader;

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeButton;

    public EmployeeListPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPIMHeaderDisplayed(WebDriver driver) {
        waitForElementVisible(driver, pimHeader);
        return isElementDisplayed(pimHeader);
    }

    public void clickToAddEmployeeButton() {
        waitForElementVisible(driver, addEmployeeButton);
        clickToElement(addEmployeeButton);
    }
}
