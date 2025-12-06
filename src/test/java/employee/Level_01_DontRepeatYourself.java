package employee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_01_DontRepeatYourself {
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
    public void Employee_01_NewEmployee() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // Login
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Chờ icon loading biến mất
        Assert.assertTrue(isLoadingIconDisappear());
        // Mở PIM
        driver.findElement(By.xpath("//span[text()='PIM']//parent::a")).click();
        // Click Add Employee
        Assert.assertTrue(isLoadingIconDisappear());
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        // Nhập thông tin
        Assert.assertTrue(isLoadingIconDisappear());
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");
        System.out.println(employeeID);
        driver.findElement(By.xpath("//p[text()='Create Login Details']//following-sibling::div//span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed();
        Assert.assertTrue(isLoadingIconDisappear());
        // Verify Personal Detail
        Assert.assertTrue(isLoadingIconDisappear());
        Assert.assertEquals(driver.findElement(By.name("firstName")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.name("lastName")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getDomProperty("value"), employeeID);
    }

    private boolean isLoadingIconDisappear() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
