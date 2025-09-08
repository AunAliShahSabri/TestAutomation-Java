package stepdefinitions;

import Pages.Dashboard;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import util.TestContext;

import java.time.Duration;


public class RegistrationSteps {
    private WebDriver driver;
    Dashboard dashboard;

//    @Before
//    public void browserSetup(){
//        System.setProperty("webdriver.chrome.driver","src/main/resources/Driver/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    }

    @Given("I am on the Registration page")
    public void iAmOnTheRegistrationPage() {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
        TestContext.generateRandomUser(); //Generates random values to be used by registerUser.
    }

    @When("I complete the form with valid data")
    public void iCompleteTheFormWithValidData() {
        dashboard = new Dashboard(driver);
        dashboard.registerUser(TestContext.FIRSTNAME,TestContext.LASTNAME, TestContext.ADDRESS,
                TestContext.CITY,TestContext.STATE,TestContext.ZIP,TestContext.NUMBER,TestContext.SSN,
                TestContext.USERNAME,TestContext.PASSWORD,TestContext.PASSWORD);
    }

    /* For Paremeterization and Data Driven Testing...

    @When("I complete the form with valid {string},{string},{string}")
    public void iCompleteTheFormWithValid(String username, String password, String confirmpassword) {
        Dashboard dashboard = new Dashboard(driver);
        dashboard.registerUser(TestContext.FIRSTNAME,TestContext.LASTNAME, TestContext.ADDRESS,
                TestContext.CITY,TestContext.STATE,TestContext.ZIP,TestContext.NUMBER,TestContext.SSN,
                username,password,confirmpassword);
    }*/

    @And("I submit the registration")
    public void iSubmitTheRegistration() {
        Dashboard dashboard = new Dashboard(driver);
        dashboard.clickRegisterBtn();
    }

    @Then("I should see a success message")
    public void iShouldSee() {
        String actualmsg = "Your account was created successfully. You are now logged in.";
        String msg  = driver.findElement(By.cssSelector("#rightPanel > p")).getText();
        Assert.assertEquals(msg,actualmsg);
    }

//    @After
//    public void tearDown(){
//        driver.close();
//        driver.quit();
//    }
}
