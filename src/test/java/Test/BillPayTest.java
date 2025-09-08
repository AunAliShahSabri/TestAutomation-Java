package Test;

import Base.baseClass;
import Pages.BillPay;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import util.TestContext;

public class BillPayTest extends baseClass {
    @Test(groups = "Bill", dependsOnGroups = "Funds")
    public void TestBillPay(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Bill.");
        test.info("TestBillPay - Bill Payment.");
        System.out.println("TestBillPay(BillPayTest.java) - Eighth Test to be executed.");
        BillPay billPay = new BillPay(driver);
        billPay.payee_information(TestContext.PAYEENAME,TestContext.ADDRESS,TestContext.CITY,
                TestContext.STATE,TestContext.ZIP,TestContext.NUMBER,TestContext.ACCOUNTNUMBER,
                TestContext.ACCOUNTNUMBER,TestContext.AMOUNT);
        billPay.selectAccount();
        billPay.getAccount();
        billPay.paymentVerification();
        test.log(Status.PASS,"Bill paid successfully.");
    }
}
