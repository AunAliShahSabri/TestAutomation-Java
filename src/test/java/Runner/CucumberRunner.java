package Runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import java.time.Duration;

@CucumberOptions(tags = "",
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions"},
        plugin = {"pretty","html:target/htmlReport.html"})

public class CucumberRunner extends AbstractTestNGCucumberTests {
    public static WebDriver driver;

    @Override
    @DataProvider(parallel = false) // This is the crucial part
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Before
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
