package Base;

import Pages.Dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class baseClass {
    public static WebDriver driver; //WebDriver object

    @BeforeSuite
    public Dashboard setUp(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe"); //add .exe while using windows

        driver = new ChromeDriver(); //instantiating chromeDriver object
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //implicit wait

        //Since, by clicking register buton changes webpage,
        // it is added in this class and the function has a return value of the next class i.e. Dashboard
        By registerBtn = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");
        driver.findElement(registerBtn).click();
        return new Dashboard(driver);
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        if(driver!= null){
            Thread.sleep(3000);
            driver.quit();
        }
    }
}
