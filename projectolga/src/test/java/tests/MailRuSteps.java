package tests;

import Utils.*;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.After;
import pages.MailRuPage;

import java.sql.SQLException;

import static Utils.RandomStringGeneratorUtil.*;
import static Utils.SQLOperations.getEmail;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class MailRuSteps {
    private static final String MAIN_URL = "https://mail.ru/";
    private MailRuPage mailRuPage;
    private WebDriver driver;

    static String folderName;
    ConnectionPool connectionPool = null;
    {
        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MailRuSteps()
    {
        driver = DriverManager.getDriver();
        mailRuPage = new MailRuPage(driver);
    }

    @Before
    public void before() {
        driver.get(MAIN_URL);
    }

    @Given("^I login in mail ru page$")
    public void loginInMailRu() {
        mailRuPage.doLogin();
    }

    @When("^I create new email$")
    public void createEmailToGroup() {
        Email email = TestDataUtil.getEmailsToSend().get(0);
        mailRuPage.createAnEmail(getEmail(3,connectionPool), getEmail(4,connectionPool), email);
    }

    @Then("^I see email in 'Отправленные' folder$")
    public void checkSentFolder() {
        assertTrue((mailRuPage.openFolderAndFindElement(mailRuPage.sentFolder)).isDisplayed());
    }

    @When("^I move email to spam$")
    public void moveEmailToSpam() {
        mailRuPage.moveEmailToSpam();
    }

    @Then("^I see email in 'Спам' folder$")
    public void checkSpamFolder() {
        assertTrue((mailRuPage.openFolderAndFindElement(mailRuPage.spamFolder)).isDisplayed());
    }

    @When("^I move email from spam$")
    public void moveEmailFromSpam() {
        mailRuPage.moveEmailFromSpam();
    }

    @When("^I mark three emails$")
    public void markTreeEmails() {
        mailRuPage.markEmail(3);
    }

    @When("^I unmark emails$")
    public void unmarkAllEmails() {
        mailRuPage.unmarkEmails();
    }

    @When("^I create new folder in main menu$")
    public void createNewFolder() {
        folderName = randomChars(5);
        mailRuPage.createNewFolder(folderName);
    }

    @When("^I delete new created folder$")
    public void deleteCreatedFolder() {
        mailRuPage.deleteFolder(folderName);
    }

    @Then("^I don't see email in 'Входящие' folder$")
    public void i_don_t_see_email_in_Входящие_folder() {
        assertTrue(mailRuPage.openFolderAndCheckElementNotExist(mailRuPage.inboxFolder));
    }

    @Then("^I don't see email in 'Спам' folder$")
    public void i_don_t_see_email_in_Спам_folder() {
        assertTrue(mailRuPage.openFolderAndCheckElementNotExist(mailRuPage.spamFolder));
    }

    @Then("^I see email in 'Входящие' folder$")
    public void i_see_email_in_Входящие_folder() {
        assertTrue((mailRuPage.openFolderAndFindElement(mailRuPage.inboxFolder)).isDisplayed());
    }

    @Then("^three emails is marked$")
    public void three_emails_is_marked() {
        assertTrue(mailRuPage.amountOfMarkedEmails() >= 3);
    }

    @Then("^all email is unmarked$")
    public void all_email_is_unmarked() {
        assertTrue(mailRuPage.checkThatEmailsNotMarked());
    }

    @Then("^I see new folder in main menu$")
    public void new_folder_in_menu() {
        assertTrue(mailRuPage.checkFolder(folderName));
    }

    @Then("^folder is deleted$")
    public void new_folder_deleted() {
        mailRuPage.checkFolderNotExist(folderName);
    }

    @After
    public void afterClass() {
        DriverManager.quitDriver();
    }
}
