package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class UpdateContactInfo {
    public WebDriver driver;

    public UpdateContactInfo(WebDriver driver){
        this.driver = driver;
    }
    public RequestLoan updateProfile(String firstName, String lastName, String address, String city, String state, String ZIP, String  number){
        WebElement fname = driver.findElement(By.id("customer.firstName"));
        fname.clear(); //clearing text field before entering new text
        fname.sendKeys(firstName);

        WebElement lname = driver.findElement(By.id("customer.lastName"));
        lname.clear(); //clearing text field before entering new text
        lname.sendKeys(lastName);

        WebElement Address = driver.findElement(By.id("customer.address.street"));
        Address.clear(); //clearing text field before entering new text
        Address.sendKeys(address);

        WebElement City = driver.findElement(By.id("customer.address.city"));
        City.clear(); //clearing text field before entering new text
        City.sendKeys(city);

        WebElement State = driver.findElement(By.id("customer.address.state"));
        State.clear(); //clearing text field before entering new text
        State.sendKeys(state);

        WebElement zip = driver.findElement(By.id("customer.address.zipCode"));
        zip.clear(); //clearing text field before entering new text
        zip.sendKeys(ZIP);

        WebElement Number = driver.findElement(By.id("customer.phoneNumber"));
        Number.clear(); //clearing text field before entering new text
        Number.sendKeys(number);

        //Update Profile button
        driver.findElement(By.cssSelector("#updateProfileForm > form > table > tbody > tr:nth-child(8) > td:nth-child(2) > input")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Navigating to Request Loan page.
        driver.findElement(By.cssSelector("#leftPanel > ul > li:nth-child(7) > a")).click();
        return new RequestLoan(driver);
    }
}
