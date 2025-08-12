package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class Login_06_Page_Manager_III extends BaseTest {

    @Parameters({"appUrl","browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName) {
//        this.appUrl = appURL;
        driver = getBrowserDriver(appUrl, browserName);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        adminUser="automationtest";
        adminPassword="Testauto1104@";
        employeeFirstName="Thuy";
        employeeLastName="Nguyen";
    }
    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToUsernameTextBox(adminUser);
        loginPage.enterToPasswordTextBox(adminPassword);
        dashboardPage = loginPage.clickToLoginButton();

        //Action of Dashboard
        //dashboardPage = new DashboardPageObject(driver);
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSeconds(2);
        employeeListPage = dashboardPage.clickToPIMModule();

        //Action of EmployeeList
        //employeeListPage = new EmployeeListPageObject(driver);
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();

        //Action of AddEmployee
        //addEmployeePage = new AddEmployeePageObject(driver);
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        personalDetailPage = addEmployeePage.clickToSaveButton();
        //Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        //Action of Personal Detail
        //personalDetailPage = new PersonalDetailPageObject(driver);
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextBoxValue(), employeeID);

    }

    @Test
    public void Employee_02_Contact_Detail() {
        contactDetailPage = personalDetailPage.openContactDetailPage();
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
    private ContactDetailPageObject contactDetailPage;
//    private PageGeneratorManager pageGenerator;
    private String employeeID, adminUser, adminPassword, employeeFirstName, employeeLastName;

}
