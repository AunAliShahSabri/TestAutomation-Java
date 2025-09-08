/*
 * Subsequent Test class for AccountOpening.
 * It contains all the Test methods to be executed by AccountOpening class
 * */
package Test;

import Base.baseClass;
import Pages.AccountOpening;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class AccountOpeningTest extends baseClass {

    @Test(groups = "Opening", dependsOnGroups = "Activity")
    public void openAccount(){
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());
        ExtentTest test = extent.createTest("Opening.");
        test.info("openAccount - Opening an account");
        System.out.println("openAccount(AccountsOpeningTest.java) - Sixth Test to be executed.");
        AccountOpening accountOpening = new AccountOpening(driver);
        WebElement dynamicValue = driver.findElement(By.id("fromAccountId")); //locator
        Select amountSelect = new Select(dynamicValue); //Stores in Select variable
        String Amount = amountSelect.getOptions().getFirst().getText(); //selects the first element in the dropdown list.
        accountOpening.selectAccountnAmount("SAVINGS",Amount);
        accountOpening.getAccountnAmount();
        accountOpening.accountCreation();
        test.log(Status.PASS,"Account Opened Successfully.");
    }
}
