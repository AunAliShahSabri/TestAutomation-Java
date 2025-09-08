/*
* This is the Dashboard class that executes after the browser is opened by base class.
* It has locators for registering user in parabank and their respective methods to get the text via sendkeys
* accountsOverviewMethod() is executed after the user is logged in.
* */
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Dashboard {
    public WebDriver driver;

    //locators
    private By custLname = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zipCode = By.id("customer.address.zipCode");
    private By phNumber = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    public  By usrName = By.id("customer.username");
    public  By pswd = By.id("customer.password");
    private By pswd_confirm = By.id("repeatedPassword");
    private By registerBtn_confirm = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");
    private By successmessage = By.cssSelector("#rightPanel > p");


    //constructor
    public Dashboard(WebDriver driver){
        this.driver = driver;
    }

    //Actions
    public void enterFName(String firstname){
        driver.findElement(By.id("customer.firstName")).sendKeys(firstname);
    }
    public void enterLName(String lastname){
        driver.findElement(custLname).sendKeys(lastname);
    }
    public void enterAddress(String Address){
        driver.findElement(address).sendKeys(Address);
    }
    public void enterCity(String City){
        driver.findElement(city).sendKeys(City);
    }
    public void enterState(String State){
        driver.findElement(state).sendKeys(State);
    }
    public void enterZIP(String ZIP){
        driver.findElement(zipCode).sendKeys(ZIP);
    }
    public void enterPhone(String Number){
        driver.findElement(phNumber).sendKeys(Number);
    }
    public void enterSSN(String SSN){
        driver.findElement(ssn).sendKeys(SSN);
    }
    public void enterUsername(String username){
        driver.findElement(usrName).sendKeys(username);
    }
    public void enterPassword(String pass){
        driver.findElement(pswd).sendKeys(pass);
    }
    public void confirmPass(String password){
        driver.findElement(pswd_confirm).sendKeys(password);
    }
    public String clickRegisterBtn(){
        driver.findElement(registerBtn_confirm).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement banner = wait.until(ExpectedConditions.visibilityOfElementLocated(successmessage));
        return banner.getText();
    }

    //This function will execute after the user has logged-in.
    public void accountsOverviewMethod(){
        new AccountsOverview(driver);
    }

    public void registerUser(String firstname, String lastname, String Address, String City, String State, String ZIP, String Number, String SSN, String username, String pass, String password){
        enterFName(firstname);
        enterLName(lastname);
        enterAddress(Address);
        enterCity(City);
        enterState(State);
        enterZIP(ZIP);
        enterPhone(Number);
        enterSSN(SSN);
        enterUsername(username);
        enterPassword(pass);
        confirmPass(password);
    }
     public void viewAccounts(){
        accountsOverviewMethod();
     }
}
