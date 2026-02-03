package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPO extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(css = "input[name='username']")
    private WebElement userNameTextbox;

    @FindBy(css = "input[name='password']")
    private WebElement passwordTextbox;

    @FindBy(css = "button.orangehrm-login-button")
    private WebElement loginButton;

    public LoginPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToUserNameTextbox(String adminUsername) {
        waitForElementVisible(driver, userNameTextbox);
        sendKeyToElement(userNameTextbox, adminUsername);
    }

    public void enterToPasswordTextbox(String adminPassword) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox, adminPassword);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, loginButton);
        clickToElement(loginButton);
    }
}
