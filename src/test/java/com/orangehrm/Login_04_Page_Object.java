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
    public void beforeClass(String appURL, String browserName) {
//        this.appUrl = appURL;
        driver = getBrowserDriver(appURL, browserName);
        loginPage = new LoginPageObject();
    }
    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToUsernameTextBox("Admin");
        loginPage.enterToPasswordTextBox("admin123");
        loginPage.clickToLoginButton();

        //Action of Dashboard
        dashboardPage = new DashboardPageObject();
        dashboardPage.clickToPIMModule();

        //Action of EmployeeList
        employeeListPage = new EmployeeListPageObject();
        employeeListPage.clickToAddEmployeeButton();

        //Action of AddEmployee
        addEmployeePage = new AddEmployeePageObject();
        addEmployeePage.enterToFirstNameTextbox("");
        addEmployeePage.enterToLastNameTextbox("");
        employeeID = addEmployeePage.getEmployeeID();
        addEmployeePage.clickToSaveButton();

        //Action of Personal Detail
        personalDetailPage = new PersonalDetailPageObject();
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),"");

        Assert.assertEquals(personalDetailPage.getEmployeeIDTextBoxValue(), employeeID);

    }

    @Test
    public void Employee_02_EditEmployee() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
//    private String appUrl;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID;

}
