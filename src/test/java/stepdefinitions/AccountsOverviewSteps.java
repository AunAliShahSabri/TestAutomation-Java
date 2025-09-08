package stepdefinitions;

import Pages.AccountsOverview;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class AccountsOverviewSteps {
    WebDriver driver;
    AccountsOverview accountsOverview;
    @Given("User is on Accounts Overview page")
    public void userIsOnAccountsOverviewPage() {
        accountsOverview = new AccountsOverview(driver);
    }

    @When("User clicks on the Account Number")
    public void userClicksOnTheAccountNumber() {
        accountsOverview.account_Activity();
    }

    @And("Selects {string} and {string} from the dropdowns")
    public void selectsAndFromTheDropdowns(String Month, String Type) {
        accountsOverview.selectOptions(Month, Type);
    }

    @Then("Upon clicking GO transaction detail should be returned")
    public void uponClickingGOTransactionDetailShouldBeReturned() {
        accountsOverview.getSelectedOptions();
    }
}
