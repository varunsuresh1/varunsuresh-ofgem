package cucumber.Stepdefs;
import MainClass.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ofgem extends Base {

    @Given("I am a Of gem customer")
    public void i_am_a_Of_gem_customer() {
        initiate();
    }
    @When("I access my Mail Id and find ofgem mail I delete it")
    public void i_access_my_Mail_Id_and_find_ofgem_mail_I_delete_it() {
        Checkmail a = new Checkmail();
        a.MailCheck();
    }
    @Then("I delete my cookies")
    public void i_delete_my_cookies() {
        driver.manage().deleteAllCookies();
    }
}
