/*
 * Subsequent Test class for stepdefinitions.AccountsOverview.
 * It contains all the Test methods to be executed by stepdefinitions.AccountsOverview class
 * */
package Test;

import Base.baseClass;
import Pages.AccountsOverview;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class AccountsOverviewTest extends baseClass {

    @Test(groups = "Activity", dependsOnGroups = "Accounts Overview")
    public void checkAccountActivity(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Activity.");
        test.info("checkAccountActivity - Checking Account Activity.");
        System.out.println("checkAccountActivity(AccountsOverviewTest.java) - Fifth Test to be executed.");
        AccountsOverview accountsOverview = new AccountsOverview(driver);
        accountsOverview.account_Activity();
        accountsOverview.selectOptions("May","Credit");
        accountsOverview.getSelectedOptions();
        accountsOverview.hoverOverTitle();
        test.log(Status.PASS,"Account Activity checked.");

    }

}
