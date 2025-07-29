package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Dashboard {
    public WebDriver driver;

    //locators
    //private By registerBtn = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");
    private By custFname = By.id("customer.firstName");
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
    private By logout_user = By.cssSelector("#leftPanel > ul > li:nth-child(8) > a");


    //constructor
    public Dashboard(WebDriver driver){
        this.driver = driver;
    }

    //Actions
    public void enterFName(String firstname){
        driver.findElement(custFname).sendKeys(firstname);
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
    public void clickRegisterBtn(){
        driver.findElement(registerBtn_confirm).click();
    }
    public void successMessage(){
        String msgExpeceted = "Your account was created successfully. You are now logged in.";
        //Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement banner = wait.until(ExpectedConditions.visibilityOfElementLocated(successmessage));
        //Hard Assertion
        Assert.assertEquals(banner.getText().trim(),msgExpeceted, "Success message mismatch");
    }
    public LoginPage user_logout(){
        driver.findElement(logout_user).click();
        return new LoginPage(driver);
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
        clickRegisterBtn();
        successMessage();
        user_logout();
    }
}
