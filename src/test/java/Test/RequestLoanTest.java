package Test;

import Base.baseClass;
import Pages.RequestLoan;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import util.TestContext;

public class RequestLoanTest extends baseClass {

    @Test(dependsOnGroups = "Update")
    public void testRequestLoan(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Loan.");
        test.info("testRequestLoan - Requesting loan from the bank.");
        System.out.println("testRequestLoan(RequestLoanTest.java) - Eleventh Test to be executed.");
        RequestLoan requestLoan = new RequestLoan(driver);
        requestLoan.requestLoan(TestContext.LOANAMOUNT,TestContext.DOWNPAYMENT);
        requestLoan.selectAccount();
        requestLoan.getAccount();
        requestLoan.loanApply();
        requestLoan.user_Logout();
        test.log(Status.PASS,"Loan Request completed successfully and user logs out.");
    }
}
