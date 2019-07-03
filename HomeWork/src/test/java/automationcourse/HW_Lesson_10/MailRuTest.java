package automationcourse.HW_Lesson_10;

import automationcourse.pages.LoginPage;
import automationcourse.pages.MailRuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static automationcourse.Utils.RandomStringGeneratorUtil.randomChars;

public class MailRuTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MailRuPage mailRu;
    private final String LOGIN = "o.rya@inbox.ru";
    private final String PASSWORD = "X7013107x&";
    private String topic;

    private String email_1 = "o.rya@inbox.ru";
    private String email_2 = "6525188@gmail.com";
    private String email_3 = "__lola__@tut.by";

    @BeforeClass
    public void before() {
        System.setProperty("webdriver.chrome.driver", "D:/automation_course/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.ru/");
        loginPage = new LoginPage(driver);
        mailRu = new MailRuPage(driver);
        loginPage.doLogin(LOGIN, PASSWORD);
    }

    @Test
    public void createEmailToGroup() {
        topic = randomChars(6);
        mailRu.createAnEmail(topic, email_1, email_2, email_3);
    }

    @Test(dependsOnMethods = "createEmailToGroup")
    public void moveEmailToSpam(){
        mailRu.moveEmailToSpam(topic);
    }

    @Test(dependsOnMethods = "moveEmailToSpam")
    public void moveEmailFromSpam() {
        mailRu.moveEmailFromSpam(topic);
    }

    @Test(dependsOnMethods = "moveEmailFromSpam")
    public void markTreeEmails() {
        mailRu.markEmail(3);
    }

    @Test(dependsOnMethods = "moveEmailFromSpam")
    public void unmarkAllEmails() {
        mailRu.unmarkEmails();
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
