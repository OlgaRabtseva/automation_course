package automationcourse.HW_Lesson_12;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

import static org.testng.Assert.assertTrue;

public class MailRuSteps {
    private static final String MAIN_URL = "https://mail.ru/";
    private MailRuPage mailRuPage;
    private WebDriver webDriver;

    private String email_1 = "o.rya@inbox.ru";
    private String email_2 = "6525188@gmail.com";

    public MailRuSteps()
    {
        System.setProperty("webdriver.chrome.driver", "D:/automation_course/chromedriver/chromedriver.exe");
        webDriver = new ChromeDriver();
        mailRuPage = new MailRuPage(webDriver);
    }
    @Before
    public void before() {
        webDriver.manage().window().maximize();
        webDriver.get(MAIN_URL);
        mailRuPage.doLogin();
    }

    @Given("^I login in mail ru page$")
    public void loginInMailRu() {}

    @When("^I create new email$")
    public void createEmailToGroup() {
        mailRuPage.createAnEmail(email_1, email_2);
    }

    @Then("^I see email in 'Отправленные' folder$")
    public void checkSentFolder() {
        assertTrue((mailRuPage.openFolderAndFindElement(mailRuPage.sentFolder)).isDisplayed());
    }

    @When("^I move email to spam$")
    public void moveEmailToSpam(){
        mailRuPage.moveEmailToSpam();
    }

    @Then("^I see email in 'Спам' folder$")
    public void checkSpamFolder() {
        boolean elExist = (mailRuPage.openFolderAndFindElement(mailRuPage.sentFolder)).isDisplayed();
        if(elExist) {
            assertTrue(elExist);
        } else {
            makeScreenshot();
        }
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

    @Then("^I don't see email in 'Входящие' folder$")
    public void i_don_t_see_email_in_Входящие_folder() {
        boolean elNotExist = mailRuPage.openFolderAndCheckElementNotExist(mailRuPage.inboxFolder);
        if(elNotExist) {
            assertTrue(elNotExist);
        } else {
            makeScreenshot();
        }
    }

    @Then("^I don't see email in 'Спам' folder$")
    public void i_don_t_see_email_in_Спам_folder() {
        boolean elNotExist = mailRuPage.openFolderAndCheckElementNotExist(mailRuPage.spamFolder);
        if(elNotExist) {
            assertTrue(elNotExist);
        } else {
            makeScreenshot();
        }
    }

    @Then("^I see email in 'Входящие' folder$")
    public void i_see_email_in_Входящие_folder() {
        boolean elExist = (mailRuPage.openFolderAndFindElement(mailRuPage.inboxFolder)).isDisplayed();
        if(elExist) {
            assertTrue(elExist);
        } else {
            makeScreenshot();
        }
    }

    @Then("^three emails is marked$")
    public void three_emails_is_marked() {
        boolean amount = mailRuPage.amountOfMarkedEmails() >= 3;
        if(amount) {
            assertTrue(amount);
        } else {
            makeScreenshot();
        }
    }

    @Then("^all email is unmarked$")
    public void all_email_is_unmarked() {
        boolean notMarked = mailRuPage.checkThatEmailsNotMarked();
        if(notMarked) {
            assertTrue(notMarked);
        } else {
            makeScreenshot();
        }
    }

    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot()
    {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @After
    public void afterClass()
    {
        webDriver.quit();
    }
}
