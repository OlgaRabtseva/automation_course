package tests;

import Utils.DriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;

public class LoginSteps {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "o.rya@inbox.ru";
    private static final String PASSWORD = "X7013107x&";
    private LoginPage loginPage;
    private WebDriver driver;

    public LoginSteps(){
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
    }

    @Before
    public void before() {
    }

    @Given("^I am on main application page$")
    public void loadMainPage()
    {
        driver.get(MAIN_URL);
    }

    @When("^I login as correct user$")
    public void loginAsCorrectUser()
    {
        loginPage.enterLoginAndPass(LOGIN, PASSWORD);
        loginPage.clickEnterButton();
    }

    @When("^I login as user with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginAsUserWithCredentials(String name, String password)
    {
        loginPage.enterLoginAndPass(name, password);
        loginPage.clickEnterButton();
    }

    @Then("I see logout link")
    public void seeLogoutLink()
    {
        assertTrue(loginPage.logoutLinkPresents());
    }

    @Then("I see error message")
    public void seeErrorMessage()
    {
        assertTrue(loginPage.logoutLinkErrorPresents());
    }

    @After
    public void afterClass()
    {
        DriverManager.quitDriver();
    }
}
