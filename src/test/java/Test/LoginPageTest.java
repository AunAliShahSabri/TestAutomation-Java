package Test;

import Pages.Dashboard;
import Pages.LoginPage;
import Base.baseClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginPageTest extends baseClass{

    //@Test(dependsOnMethods = "Test.DashboardTest.testValidRegistration")
    public void ResistrationSuccess(){
        LoginPage loginpage = new LoginPage(driver);
//        loginpage.re_Username(username);
//        loginpage.re_pass(pass);
//        loginpage.usr_login();

    }

}
