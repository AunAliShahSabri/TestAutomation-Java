/*
 * This is the Request Loan class that is executed after the Update Contact Info class
 * This is the Apply for a loan page for Parabank
 * This class has functions that asks user to enter loan amount and down payment to apply for a loan.
 */
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RequestLoan {
    public WebDriver driver;

    public RequestLoan(WebDriver driver){
        this.driver = driver;
    }

    public void requestLoan(String loanAmount, String downPayment){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement amount = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("amount"))));
        amount.sendKeys(loanAmount);
        WebElement down = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("downPayment"))));
        down.sendKeys(downPayment);
    }

    //This function is created to get the element for the dropdown so that it can be reused
    private Select account(){
        //returning select from account dropdown.
        return new Select(driver.findElement(By.id("fromAccountId")));
    }

    /*selectByVisibleText(...) tells Selenium to pick the dropdown option
    matching the exact visible label in the HTML.*/
    public void selectAccount(){
        WebElement accountDropDown = driver.findElement(By.id("fromAccountId"));
        Select dropDown = new Select(accountDropDown);
        String value = dropDown.getOptions().getLast().getText();
        account().selectByVisibleText(value);
    }

    /*getFirstSelectedOption() → gets the currently selected <option> element inside the dropdown.
    .getText() → reads the visible text from that <option>.*/
    public void getAccount(){
        account().getFirstSelectedOption();
    }

    public void loanApply(){
        //Apply Loan button clicked
        driver.findElement(By.cssSelector("#requestLoanForm > form > table > tbody > tr:nth-child(4) > td:nth-child(2) > input")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //getting success message.
        String actualMessage =driver.findElement(By.cssSelector("#loanRequestApproved > p:nth-child(1)")).getText();
        Assert.assertEquals(actualMessage, "Congratulations, your loan has been approved."
                ,"Loan not approved.");

        //clicking new account id created after if the loan was accepted.
        driver.findElement(By.id("newAccountId")).click();
    }

    //Logging Out the user after testing all the major functions available on the website.
    public void user_Logout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.cssSelector("#leftPanel > ul > li:nth-child(8) > a")).click(); //logout
    }
}
