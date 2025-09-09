/*
 * This is the Bill Payment Service class that is executed after the Funds Transfer class
 * This is the Accounts Overview page for Parabank
 * This class has functions that asks User to enter details like name, address, city, state, zip, phone number.
 * Also asks user to enter account number and verify it before entering amount to pay bill.
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

public class BillPay {
    public WebDriver driver;

    public BillPay(WebDriver driver){
        this.driver = driver;
    }
    public void payee_information(String payeeName, String address, String city, String state, String zip, String number, String account, String verifyAccount, String amount){
        driver.findElement(By.cssSelector("#billpayForm > form > table > tbody > tr:nth-child(1) > td:nth-child(2) > input")).sendKeys(payeeName);
        driver.findElement(By.cssSelector("#billpayForm > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > input")).sendKeys(address);
        driver.findElement(By.cssSelector("#billpayForm > form > table > tbody > tr:nth-child(3) > td:nth-child(2) > input")).sendKeys(city);
        driver.findElement(By.cssSelector("#billpayForm > form > table > tbody > tr:nth-child(4) > td:nth-child(2) > input")).sendKeys(state);
        driver.findElement(By.cssSelector("#billpayForm > form > table > tbody > tr:nth-child(5) > td:nth-child(2) > input")).sendKeys(zip);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/form/table/tbody/tr[6]/td[2]/input")).sendKeys(number);
        driver.findElement(By.cssSelector("#billpayForm > form > table > tbody > tr:nth-child(8) > td:nth-child(2) > input")).sendKeys(account);
        driver.findElement(By.cssSelector("#billpayForm > form > table > tbody > tr:nth-child(9) > td:nth-child(2) > input")).sendKeys(verifyAccount);
        driver.findElement(By.cssSelector("#billpayForm > form > table > tbody > tr:nth-child(11) > td:nth-child(2) > input")).sendKeys(amount);

    }

    //This function is created to get the element for the dropdown so that it can be reused
    private Select account(){
        //returning select from account dropdown.
        return new Select(driver.findElement(By.name("fromAccountId")));
    }

    /*selectByVisibleText(...) tells Selenium to pick the dropdown option
    matching the exact visible label in the HTML.*/
    public void selectAccount(){
        WebElement accountDropDown = driver.findElement(By.name("fromAccountId"));
        Select dropDown = new Select(accountDropDown);
        String value = dropDown.getOptions().getFirst().getText();
        account().selectByVisibleText(value);
    }

    /*getFirstSelectedOption() → gets the currently selected <option> element inside the dropdown.
    .getText() → reads the visible text from that <option>.*/
    public void getAccount(){
        account().getFirstSelectedOption();
        //clicking SEND PAYMENT button
        driver.findElement(By.cssSelector("#billpayForm > form > table > tbody > tr:nth-child(14) > td:nth-child(2) > input")).click();
    }

    public FindTransactions paymentVerification(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); //Waiting for the element in DOM to be visible
        String payeeName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("payeeName"))).getText();
        String amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amount"))).getText();
        String fromAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fromAccountId"))).getText();

        //Storing the success message in the variable for assertion.
        String completionMessage = "Bill Payment to " +payeeName+ " in the amount of " +amount+ " from account " +fromAccount+ " was successful.";
        //Getting the dynamic success message from Webpage.
        String actualMessage = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[2]/p[1]")).getText();
        Assert.assertEquals(completionMessage, actualMessage);

        return new FindTransactions(driver);
    }
}
