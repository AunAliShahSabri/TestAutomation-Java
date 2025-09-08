package Test;

import Base.baseClass;
import Pages.FundsTransfer;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class FundsTransferTest extends baseClass {

    @Test(groups = "Funds", dependsOnGroups = "Opening")
    public void TestFundsTransfer(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Funds");
        test.info("TestFundsTransfer - Transferring Funds.");
        System.out.println("TestFundsTransfer(FundsTransferTest.java) - Seventh Test to be executed.");
        FundsTransfer fundstransfer = new FundsTransfer(driver);
        //Keys are used as keyboard keys for input. Inserting 3 backspace keys removes *22 from the input.
        fundstransfer.transferFunds("100*22"+ Keys.chord(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE));
        fundstransfer.selectAccounts();
        fundstransfer.getAccounts();
        fundstransfer.confirmTransfer();
        fundstransfer.billPay();
        test.log(Status.PASS,"Funds transferred successfully.");
    }
}
