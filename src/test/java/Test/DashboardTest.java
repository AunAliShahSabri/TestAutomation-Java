/*
* Subsequent Test class for Dashboard.
* It contains all the Test methods to be executed by Dashobard class
* */
package Test;

import Base.baseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.Dashboard;
import util.TestContext;

public class DashboardTest extends baseClass {

    @Test(groups = "registration")
    public void testValidRegistration(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Registration");
        test.info("testValidRegistration - Registering A User.");
        System.out.println("testValidRegistration(DashboardTest.java) - First Test to be executed.");
        TestContext.generateRandomUser(); //Generates random values to be used by registerUser.
        Dashboard dashboard = new Dashboard(driver);
        dashboard.registerUser(TestContext.FIRSTNAME,TestContext.LASTNAME, TestContext.ADDRESS,
                TestContext.CITY,TestContext.STATE,TestContext.ZIP,TestContext.NUMBER,TestContext.SSN,
                TestContext.USERNAME,TestContext.PASSWORD,TestContext.PASSWORD);
        Assert.assertTrue(dashboard.clickRegisterBtn().contains("Your account was created successfully. You are now logged in."),
                "User is already registered");
        test.log(Status.PASS,"Test Passed and the User is Registered.");
    }

    @Test(groups = "Accounts Overview", dependsOnGroups = "Accounts")
    public void accountsOverviewTest(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Accounts Overview");
        test.info("accountsOverviewTest - Overviewing Account Details.");
        System.out.println("accountsOverviewTest(DashboardTest.java) - Fourth Test to be executed.");
        Dashboard dashboard = new Dashboard(driver);
        dashboard.viewAccounts();
        test.log(Status.PASS,"Accounts Viewed.");
    }
}
