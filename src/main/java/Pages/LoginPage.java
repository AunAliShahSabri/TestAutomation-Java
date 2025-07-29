package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {

    public final WebDriver driver;

    private By reEnterUsrname = By.cssSelector("#loginPanel > form > div:nth-child(2) > input");
    private By reEnterpass = By.cssSelector("#loginPanel > form > div:nth-child(4) > input");
    private By loginBtn = By.cssSelector("#loginPanel > form > div:nth-child(5) > input");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void re_Username(String reusername){
        driver.findElement(reEnterUsrname).sendKeys(reusername);
    }

    public void re_pass(String repassword){
        driver.findElement(reEnterpass).sendKeys(repassword);
    }

    public void usr_login(){
        driver.findElement(loginBtn).click();
    }
}
