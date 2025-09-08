/*
* This is a base class that initiates the whole test executions.
* It contains Setup and teardown methods for opening and closing browser sessions.
* This opens "https://parabank.parasoft.com/parabank/index.htm" in the browser
* */
package Base;

import Pages.Dashboard;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.time.Duration;

public class baseClass {
    public static WebDriver driver; //WebDriver object
    public static ExtentReports extent;
    public static ExtentTest test;

    //Initializing Extent Reports to be generated at the end by the name of "ExtentReport.html" in resources/Reports directory.
    @BeforeSuite(alwaysRun = true)
    public void extentReportInitialization(){
        ExtentSparkReporter spark = new ExtentSparkReporter("src/main/resources/Reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeSuite(alwaysRun = true)
    public Dashboard setUp(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/Driver/chromedriver.exe"); //add .exe while using windows

        driver = new ChromeDriver(); //instantiating chromeDriver object
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //implicit wait
        System.out.println("Session ID: " +((RemoteWebDriver)driver).getSessionId());

        //if an action changes the webpage, then a handle should be retuned to the new page.
        By registerBtn = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");
        driver.findElement(registerBtn).click();
        return new Dashboard(driver);
    }

    //Takes screenshot of the browser. Will only execute if method fails.
    //@AfterMethod(alwaysRun = true)
    public void takeScreenShot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()){
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            System.out.println("Screenshot taken!" + screenshot.getPath());
            try {
                Files.move(screenshot, new File("src/main/resources/Screenshots/test")); //moving SS in a folder in the project.
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return options;
    }

    private void setCookie(){
        Cookie cookie = new Cookie.Builder("tau","123")
                .domain("parabank.parasoft.com/parabank/index.htm")
                .build();
        driver.manage().addCookie(cookie);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        if (driver!= null){
            extent.flush();
            driver.quit();
        }
    }
}