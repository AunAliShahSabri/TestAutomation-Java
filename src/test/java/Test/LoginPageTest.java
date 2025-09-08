/*
 * Subsequent Test class for LoginPage.
 * It contains all the Test methods to be executed by LoginPage class
 * */
package Test;

import Pages.LoginPage;
import Base.baseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import util.TestContext;

public class LoginPageTest extends baseClass{

    @Test(dependsOnGroups = "registration")
    public void RegistrationSuccess(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("User Login");
        test.info("RegistrationSuccess - User Login.");
        System.out.println("Registration Success(LoginPageTest.java) - Second Test to be executed.");
        LoginPage loginpage = new LoginPage(driver);
        loginpage.javascript_scroller();
        loginpage.forgot_info(TestContext.FIRSTNAME,TestContext.LASTNAME,
                TestContext.ADDRESS,TestContext.CITY,TestContext.STATE,
                TestContext.ZIP,TestContext.SSN);
        loginpage.Re_loggingIN(TestContext.USERNAME,TestContext.PASSWORD);
        test.log(Status.PASS,"Test Passed and the User is Logged in.");
    }

    @Test(groups = "Accounts", dependsOnMethods = "RegistrationSuccess")
    public void AccountServices(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Accounts.");
        test.info("AccountServices");
        System.out.println("AccountServices(LoginPageTest.java) - Third Test to be executed.");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.viewDashboard();
        test.log(Status.PASS,"Account Services.");

    }
}
