package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class FundsTransfer {
    public WebDriver driver;

    public FundsTransfer(WebDriver driver){
        this.driver = driver;
    }

    public void transferFunds(String amount){
        driver.findElement(By.id("amount")).sendKeys(amount);
    }

    private Select fromAccount(){
        return new Select(driver.findElement(By.id("fromAccountId")));
    }

    private Select toAccount(){
        return new Select(driver.findElement(By.id("toAccountId")));
    }

    public void selectAccounts(){
        //Getting dynamic From account value from the dropdown
        WebElement dynamicFromValue = driver.findElement(By.id("fromAccountId"));
        Select from = new Select(dynamicFromValue);
        String fromValue = from.getOptions().getLast().getText();

        //Getting dynamic To account value from the dropdown
        WebElement dynamicToValue = driver.findElement(By.id("toAccountId"));
        Select to = new Select(dynamicToValue);
        String toValue = to.getOptions().getFirst().getText();

        fromAccount().selectByVisibleText(fromValue);
        toAccount().selectByVisibleText(toValue);
    }

    public void getAccounts(){
        fromAccount().getFirstSelectedOption();
        toAccount().getFirstSelectedOption();
    }

    public void confirmTransfer(){
        driver.findElement(By.cssSelector("#transferForm > div:nth-child(4) > input")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5)); //Waiting for the element in DOM to be visible
        String transferredAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amountResult"))).getText();
        String fromAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fromAccountIdResult"))).getText();
        String toAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toAccountIdResult"))).getText();

        //Storing the success message in the variable for assertion.
        String successMessage = transferredAmount + " has been transferred from account #" + fromAccount + " to account #" + toAccount + ".";
        //Getting the dynamic success message from Webpage.
        String actualMessage = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[2]/p[1]")).getText();
        Assert.assertEquals(actualMessage, successMessage);
    }

    public BillPay billPay(){
        //Opening Bill Pay webpage
        driver.findElement(By.cssSelector("#leftPanel > ul > li:nth-child(4) > a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new BillPay(driver);
    }
}
