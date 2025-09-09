/*
* This is the AccountsOverview class that is executed after the login class
* This is the Accounts Overview page for Parabank
* This class has functions that select Activity Period and Type in Account Activity section of Accounts Overview page.
* It also has hoverOverTitle() method to hover over the title text.
* */
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;

public class AccountsOverview {
    public WebDriver driver;

    public AccountsOverview(WebDriver driver){
        this.driver = driver;
    }

    public void account_Activity(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#accountTable > tbody > tr:nth-child(1) > td:nth-child(1) > a")));

        driver.findElement(By.cssSelector("#accountTable > tbody > tr:nth-child(1) > td:nth-child(1) > a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }

    //This function is created to get the element for the dropdown so that it can be reused
    private Select MonthDropdown(){
        return new Select(driver.findElement(By.id("month")));
    }

    //This function is created to get the element for the dropdown so that it can be reused
    private Select TypeDropdown(){
        return new Select(driver.findElement(By.id("transactionType")));
    }


    /*selectByVisibleText(...) tells Selenium to pick the dropdown option
    matching the exact visible label in the HTML.*/
    public void selectOptions(String Month, String Type){
        MonthDropdown().selectByVisibleText(Month);
        TypeDropdown().selectByVisibleText(Type);
    }

    /*getFirstSelectedOption() → gets the currently selected <option> element inside the dropdown.
    .getText() → reads the visible text from that <option>.*/
    public void getSelectedOptions(){
        MonthDropdown().getFirstSelectedOption().getText();
        TypeDropdown().getFirstSelectedOption().getText();

        //Clicking Go button under Account Activity
        driver.findElement(By.xpath("//*[@id=\"activityForm\"]/table/tbody/tr[3]/td[2]/input")).click();
        //Clicking Open New Account under Account Services
        driver.findElement(By.cssSelector("#leftPanel > ul > li:nth-child(1) > a")).click();
    }
    
    //Hovering over Parabank title
    public AccountOpening hoverOverTitle(){
        WebElement figure = driver.findElement(By.className("logo"));
        Actions actions = new Actions(driver); //takes webdriver object
        actions.moveToElement(figure).perform();
        String tooltipText = figure.getAttribute("title");
        Assert.assertEquals(tooltipText, "ParaBank");
        return new AccountOpening(driver);
    }
}
