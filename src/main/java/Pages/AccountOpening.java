/*
 * This is the AccountsOpening class that is executed after the AccountsOverview class
 * This is the Accounts Opening page for Parabank
 * This class has functions that select Type of Account and Existing Account of Accounts Opening page.
 * It also has a function to select and verify the created account
 * */
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AccountOpening {
    public WebDriver driver;

    public AccountOpening(WebDriver driver){
        this.driver = driver;
    }

    //This function is created to get the element for the dropdown so that it can be reused
    private Select AccountType(){
        return new Select(driver.findElement(By.id("type")));
    }

    //This function is created to get the element for the dropdown so that it can be reused
    private Select AccountExisting(){
        return new Select(driver.findElement(By.id("fromAccountId")));
    }

    /*selectByVisibleText(...) tells Selenium to pick the dropdown option
    matching the exact visible label in the HTML.*/
    public void selectAccountnAmount(String Type, String Amount){
        AccountType().selectByVisibleText(Type);
        AccountExisting().selectByVisibleText(Amount);
    }

    /*getFirstSelectedOption() → gets the currently selected <option> element inside the dropdown.
    .getText() → reads the visible text from that <option>.*/
    public void getAccountnAmount(){
        AccountType().getFirstSelectedOption().getText();
        AccountExisting().getFirstSelectedOption().getText();
    }

    public void accountCreation(){
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/form/div/input")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[2]/p[1]"))).getText();
        String expText = "Congratulations, your account is now open.";
        Assert.assertEquals(Text, expText);

        //Asserting whether account number is same created above in the details page.
        String newAccNumber = driver.findElement(By.id("newAccountId")).getText();
        driver.findElement((By.id("newAccountId"))).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String accNumInDetails = driver.findElement(By.id("accountId")).getText();
        Assert.assertEquals(accNumInDetails,newAccNumber); //comparing both ids text to be equal.

        //checking active transaction in the account.
        driver.findElement(By.xpath("//*[@id=\"transactionTable\"]/tbody/tr/td[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a")).click();

    }
}
