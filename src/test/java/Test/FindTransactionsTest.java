/*
 * Subsequent Test class for FindTransaction.
 * It contains all the Test methods to be executed by FindTransaction class
 * */
package Test;

import Base.baseClass;
import Pages.FindTransactions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class FindTransactionsTest extends baseClass {
    @Test(groups = "Find", dependsOnGroups = "Bill")
    public void testFindTransaction(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Find.");
        test.info("testFindTransaction - Finding A Transaction.");
        System.out.println("testFindTransaction(FindTransactionsTest.java) - Ninth Test to be executed.");
        FindTransactions findTransactionsTest = new FindTransactions(driver);
        findTransactionsTest.getAccountsOverviewData();
        findTransactionsTest.selectAccount();
        findTransactionsTest.getAccount();
        findTransactionsTest.findByDate();
        findTransactionsTest.findByDateRange();
        findTransactionsTest.findByAmount();
        test.log(Status.PASS,"Transaction found successfully.");
    }
}
