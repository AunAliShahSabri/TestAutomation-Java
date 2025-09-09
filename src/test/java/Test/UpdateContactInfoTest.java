/*
 * Subsequent Test class for UpdateContactInfo.
 * It contains all the Test methods to be executed by UpdateContactInfo class
 * */
package Test;

import Base.baseClass;
import Pages.UpdateContactInfo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import util.TestContext;

public class UpdateContactInfoTest extends baseClass {
    @Test(groups = "Update", dependsOnGroups = "Find")
    public void testUpdateContactInfo(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Update.");
        test.info("testUpdateContactInfo - Updating Contact Info.");
        System.out.println("testUpdateContactInfo(UpdateContactInfoTest.java) - Tenth Test to be executed.");
        UpdateContactInfo updateContactInfoTest = new UpdateContactInfo(driver);
        updateContactInfoTest.updateProfile(TestContext.FIRSTNAME,TestContext.LASTNAME,
                TestContext.ADDRESS,TestContext.CITY,TestContext.STATE,TestContext.ZIP,
                TestContext.NUMBER);
        test.log(Status.PASS,"Info updated successfully..");
    }

}
