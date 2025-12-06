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

public class Level_02_BasePage_II {
    private BasePage basePage;
    private WebDriver driver;
    private String firstName, lastName, password, userName, employeeID;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        basePage = BasePage.getBasePage();
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
        basePage.sendkeyToElement(driver, "//input[@name='username']", "Admin");
        basePage.sendkeyToElement(driver, "//input[@name='password']", "admin123");
        basePage.clickToElement(driver, "//button[@type='submit']");
        // Chờ icon loading biến mất
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        // Mở PIM
        basePage.clickToElement(driver, "//span[text()='PIM']//parent::a");
        // Click Add Employee
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        basePage.clickToElement(driver, "//a[text()='Add Employee']");
        // Nhập thông tin
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        basePage.sendkeyToElement(driver, "//input[@name='firstName']", firstName);
        basePage.sendkeyToElement(driver, "//input[@name='lastName']", lastName);
        employeeID = basePage.getElementDomProperty(driver, "//label[text()='Employee Id']/parent::div/following-sibling::div/input", "value");
        System.out.println(employeeID);
        basePage.clickToElement(driver, "//p[text()='Create Login Details']//following-sibling::div//span");
        basePage.sleepInSecond(2);
        basePage.sendkeyToElement(driver, "//label[text()='Username']/parent::div/following-sibling::div/input", userName);
        basePage.sendkeyToElement(driver, "//label[text()='Password']/parent::div/following-sibling::div/input", password);
        basePage.sendkeyToElement(driver, "//label[text()='Confirm Password']/parent::div/following-sibling::div/input", password);
        basePage.clickToElement(driver, "//button[contains(string(),'Save')]");
        basePage.isElementDisplayed(driver, "//p[text()='Successfully Saved']");
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        // Verify Personal Detail
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        Assert.assertEquals(basePage.getElementDomProperty(driver, "//input[@name='firstName']", "value"), firstName);
        Assert.assertEquals(basePage.getElementDomProperty(driver, "//input[@name='lastName']", "value"), lastName);
        Assert.assertEquals(basePage.getElementDomProperty(driver, "//label[text()='Employee Id']/parent::div/" +
                "following-sibling::div/input", "value"), employeeID);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
