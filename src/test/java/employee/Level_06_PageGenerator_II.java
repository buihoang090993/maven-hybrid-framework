package employee;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGeneration;
import pageObjects.orangeHRM.*;

import java.util.Random;

public class Level_06_PageGenerator_II extends BaseTest {
    private WebDriver driver;
    private String firstName, middleName, lastName, adminUsername, adminPassword, userName, password, employeeID;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPage;
    private AddEmployeePO addEmployeePage;
    private PersonalDetailsPO personalDetailPage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        firstName = "McCathay";
        middleName = "Tom";
        lastName = "John";
        adminUsername = "HoangBeo";
        adminPassword = "Buihoang12#";
        userName = firstName + new Random().nextInt(999);
        password = "Testing111###";

        loginPage = PageGeneration.getPage(LoginPO.class, driver);
    }

    @Test
    public void Employee_01_NewEmployee() {
        // Login Page
        loginPage.enterToUserNameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);
        dashboardPage = loginPage.clickToLoginButton();

        Assert.assertTrue(dashboardPage.isLoadingIconDisappear(driver));
        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed(driver));
        employeeListPage = dashboardPage.clickToPIMModule();

        Assert.assertTrue(employeeListPage.isLoadingIconDisappear(driver));
        Assert.assertTrue(employeeListPage.isPIMHeaderDisplayed(driver));

        addEmployeePage = employeeListPage.clickToAddEmployeeButton();

        Assert.assertTrue(addEmployeePage.isLoadingIconDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(firstName);
        addEmployeePage.enterToMiddleNameTextbox(middleName);
        addEmployeePage.enterToLastNameTextbox(lastName);

        employeeID = addEmployeePage.getEmployeeIDValue();

        addEmployeePage.clickToCreateLoginDetailsCheckbox();

        addEmployeePage.enterToUserNameTextbox(userName);
        addEmployeePage.enterToPasswordTextbox(password);
        addEmployeePage.enterToConfirmPasswordTextbox(password);

        addEmployeePage.clickToSaveButton();
        addEmployeePage.sleepInSecond(2);

        Assert.assertTrue(addEmployeePage.isSuccessfullySavedMessageDisplayed());
        Assert.assertTrue(addEmployeePage.isLoadingIconDisappear(driver));

        // Personal Details Page
        personalDetailPage = PageGeneration.getPage(PersonalDetailsPO.class, driver);

        Assert.assertTrue(personalDetailPage.isLoadingIconDisappear(driver));

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(personalDetailPage.getMiddleNameTextboxValue(), middleName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);

        employeeListPage = personalDetailPage.clickToEmployeeListButton();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
