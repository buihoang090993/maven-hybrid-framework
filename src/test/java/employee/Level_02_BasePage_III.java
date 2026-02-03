package employee;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_III extends BasePage{
    private WebDriver driver;
    private String firstName, lastName, password, userName, employeeID;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        firstName = "McCathay";
        lastName = "John";
        password = "McCathayJohnx99";
        userName = firstName + new Random().nextInt(999);
    }

    @Test
    public void Employee_01_NewEmployee() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // Login
        sendKeyToElement(driver, "//input[@name='username']", "Admin");
        sendKeyToElement(driver, "//input[@name='password']", "admin123");
        clickToElement(driver, "//button[@type='submit']");
        // Chờ icon loading biến mất
        Assert.assertTrue(isLoadingIconDisappear(driver));
        // Mở PIM
        clickToElement(driver, "//span[text()='PIM']//parent::a");
        // Click Add Employee
        Assert.assertTrue(isLoadingIconDisappear(driver));
        clickToElement(driver, "//a[text()='Add Employee']");
        // Nhập thông tin
        Assert.assertTrue(isLoadingIconDisappear(driver));
        sendKeyToElement(driver, "//input[@name='firstName']", firstName);
        sendKeyToElement(driver, "//input[@name='lastName']", lastName);
        employeeID = getElementDomProperty(driver, "//label[text()='Employee Id']/parent::div/following-sibling::div/input", "value");
        System.out.println(employeeID);
        clickToElement(driver, "//p[text()='Create Login Details']//following-sibling::div//span");
        sleepInSecond(2);
        sendKeyToElement(driver, "//label[text()='Username']/parent::div/following-sibling::div/input", userName);
        sendKeyToElement(driver, "//label[text()='Password']/parent::div/following-sibling::div/input", password);
        sendKeyToElement(driver, "//label[text()='Confirm Password']/parent::div/following-sibling::div/input", password);
        clickToElement(driver, "//button[contains(string(),'Save')]");
        isElementDisplayed(driver, "//p[text()='Successfully Saved']");
        Assert.assertTrue(isLoadingIconDisappear(driver));
        // Verify Personal Detail
        Assert.assertTrue(isLoadingIconDisappear(driver));
        Assert.assertEquals(getElementDomProperty(driver, "//input[@name='firstName']", "value"), firstName);
        Assert.assertEquals(getElementDomProperty(driver, "//input[@name='lastName']", "value"), lastName);
        Assert.assertEquals(getElementDomProperty(driver, "//label[text()='Employee Id']/parent::div/" +
                "following-sibling::div/input", "value"), employeeID);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
