package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.LoginUI;

public class LoginPO extends BasePage {
    private WebDriver driver;

    public LoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToUserNameTextbox(String adminUsername) {
        waitForElementVisible(driver, LoginUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, LoginUI.USERNAME_TEXTBOX, adminUsername);
    }

    public void enterToPasswordTextbox(String adminPassword) {
        waitForElementVisible(driver, LoginUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginUI.PASSWORD_TEXTBOX, adminPassword);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, LoginUI.LOGIN_BUTTON);
        clickToElement(driver, LoginUI.LOGIN_BUTTON);
    }
}
