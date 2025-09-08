package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FindTransactions {
    public WebDriver driver;

    public FindTransactions(WebDriver driver){
        this.driver = driver;
    }

    private String saveDate;
    private String Amount;

    public void getAccountsOverviewData(){
        //Navigating back to Accounts Overview page to get and save data of the transactions to be used in find transactions.
        driver.findElement(By.cssSelector("#leftPanel > ul > li:nth-child(2) > a")).click();
        //Clicking the account number that was created in the start.
        driver.findElement(By.cssSelector("#accountTable > tbody > tr:nth-child(2) > td:nth-child(1) > a")).click();
        //Clicking one of the transactions made by this account.
        driver.findElement(By.cssSelector("#transactionTable > tbody > tr:nth-child(1) > td:nth-child(2) > a")).click();

        //Saving date to be searched later.
        saveDate = driver.findElement(By.cssSelector("#rightPanel > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText();

        //Saving Amount to be searched later.
        Amount = driver.findElement(By.cssSelector("#rightPanel > table > tbody > tr:nth-child(5) > td:nth-child(2)")).getText();
        Amount = Amount.replace("$",""); //replacing the $ in the amount text.

        //Clicking Find Transactions
        driver.findElement(By.cssSelector("#leftPanel > ul > li:nth-child(5) > a")).click();
    }

    //This function is created to get the element for the dropdown so that it can be reused
    private Select account(){
        //returning select from account dropdown.
        return new Select(driver.findElement(By.xpath("//*[@id=\"accountId\"]")));
    }

    /*selectByVisibleText(...) tells Selenium to pick the dropdown option
    matching the exact visible label in the HTML.*/
    public void selectAccount(){
        WebElement accountDropDown = driver.findElement(By.xpath("//*[@id=\"accountId\"]"));
        Select dropDown = new Select(accountDropDown);
        String value = dropDown.getOptions().getFirst().getText();
        account().selectByVisibleText(value);
    }

    /*getFirstSelectedOption() → gets the currently selected <option> element inside the dropdown.
    .getText() → reads the visible text from that <option>.*/
    public void getAccount(){
        account().getFirstSelectedOption();
    }

    public void findByDate(){
        driver.findElement(By.id("transactionDate")).sendKeys(saveDate);
        driver.findElement(By.id("findByDate")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Find Transactions button
        driver.findElement(By.cssSelector("#leftPanel > ul > li:nth-child(5) > a")).click();
    }

    public void findByDateRange(){
        driver.findElement(By.id("fromDate")).sendKeys(saveDate);
        driver.findElement(By.id("toDate")).sendKeys(saveDate);
        driver.findElement(By.id("findByDateRange")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Find Transactions button
        driver.findElement(By.cssSelector("#leftPanel > ul > li:nth-child(5) > a")).click();
    }

    public UpdateContactInfo findByAmount(){
        driver.findElement(By.id("amount")).sendKeys(Amount);
        driver.findElement(By.id("findByAmount")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Navigating to Update Contact Info page
        driver.findElement(By.cssSelector("#leftPanel > ul > li:nth-child(6) > a")).click();

        return new UpdateContactInfo(driver);
    }

}
