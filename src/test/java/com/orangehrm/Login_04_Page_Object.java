package com.orangehrm;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class Login_04_Page_Object extends BaseTest {

    @Parameters({"appUrl","browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName) {
//        this.appUrl = appURL;
        driver = getBrowserDriver(appUrl, browserName);
        loginPage = new LoginPageObject(driver);
        adminUser="Admin";
        adminPassword="admin123";
        employeeFirstName="Thuy";
        employeeLastName="Nguyen";
    }
    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToUsernameTextBox(adminUser);
        loginPage.enterToPasswordTextBox(adminPassword);
        loginPage.clickToLoginButton();

        //Action of Dashboard
        dashboardPage = new DashboardPageObject(driver);
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSeconds(2);
        dashboardPage.clickToPIMModule();

        //Action of EmployeeList
        employeeListPage = new EmployeeListPageObject(driver);
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
        employeeListPage.clickToAddEmployeeButton();

        //Action of AddEmployee
        addEmployeePage = new AddEmployeePageObject(driver);
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        addEmployeePage.clickToSaveButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
        //Action of Personal Detail
        personalDetailPage = new PersonalDetailPageObject(driver);
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextBoxValue(), employeeID);

    }

    @Test
    public void Employee_02_EditEmployee() {

    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
//    private String appUrl;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID, adminUser, adminPassword, employeeFirstName, employeeLastName;

}
