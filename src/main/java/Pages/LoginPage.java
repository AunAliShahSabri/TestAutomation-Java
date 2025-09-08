/*
*This is the loginpage class that executes after the Dashboard.
* It contains forgot_info(user cant remember creds) and Re_logginIN functions
* */
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public WebDriver driver;
    private final By reEnterUsrname = By.cssSelector("#loginPanel > form > div:nth-child(2) > input");
    private final By reEnterpass = By.cssSelector("#loginPanel > form > div:nth-child(4) > input");
    private final By loginBtn = By.cssSelector("#loginPanel > form > div:nth-child(5) > input");
    private final By logout_user = By.cssSelector("#leftPanel > ul > li:nth-child(8) > a");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //A javascript scroller function using a placeholder to scroll down a table on the webpage
    //Selenium does not have a scroller function as click() and sendKeys() do the scrolling
    // so we use javascript to scroll down
    public void javascript_scroller() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Click the Services link on Dashboard
        WebElement serviceslink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#headerPanel > ul.leftmenu > li:nth-child(3) > a")));
        serviceslink.click();
        // Wait for the target panel to be visible
        WebElement scrollTarget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightPanel")));
        // Smooth scroll into center of view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", scrollTarget);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void forgot_info(String firstname, String lastname, String address, String city, String state, String ZIP, String SSN){
        driver.findElement(logout_user).click();

        //Clicking the forgot info link
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[1]/a")).click();
        driver.findElement(By.id("firstName")).sendKeys(firstname);
        driver.findElement(By.id("lastName")).sendKeys(lastname);
        driver.findElement(By.id("address.street")).sendKeys(address);
        driver.findElement(By.id("address.city")).sendKeys(city);
        driver.findElement(By.id("address.state")).sendKeys(state);
        driver.findElement(By.id("address.zipCode")).sendKeys(ZIP);
        driver.findElement(By.id("ssn")).sendKeys(SSN);
        driver.findElement(By.xpath("//*[@id=\"lookupForm\"]/table/tbody/tr[8]/td[2]/input"))
                .click();
    }

    public void Re_loggingIN(String reusername, String repassword){
        driver.findElement(logout_user).click();
        driver.findElement(reEnterUsrname).sendKeys(reusername);
        driver.findElement(reEnterpass).sendKeys(repassword);
        driver.findElement(loginBtn).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void viewDashboard(){
        new Dashboard(driver);
    }
}
