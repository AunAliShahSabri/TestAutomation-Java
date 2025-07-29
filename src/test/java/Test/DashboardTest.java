package Test;

import Base.baseClass;
import Pages.LoginPage;
import org.testng.annotations.Test;
import Pages.Dashboard;

public class DashboardTest extends baseClass {

    @Test(priority = 1)
    public void testValidRegistration(){
        String username = "benjamun";
        String pass = "benjamun";
        Dashboard dashboard = new Dashboard(driver);
        dashboard.registerUser("Aun","Ali",
                "Gulshan","Karachi","Sindh","12345","09001234567",
                "009988776655",username,pass,"benja");

        LoginPage loginpage = new LoginPage(driver);
        loginpage.re_Username(username);
        loginpage.re_pass(pass);
        loginpage.usr_login();
    }
}
