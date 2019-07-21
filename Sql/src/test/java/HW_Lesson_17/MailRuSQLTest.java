package HW_Lesson_17;

import Utils.ConnectionPool;
import Utils.SQLOperations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.MailPage;

import java.sql.SQLException;

import static Utils.SQLOperations.*;

public class MailRuSQLTest {
    WebDriver driver;
    MailPage mailPage;
    ConnectionPool connectionPool = null;
    {
        try {
            connectionPool = ConnectionPool.create();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/automation_course/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.ru");
        mailPage = new MailPage(driver);
    }

    @Test
    public void loginTest() {
        mailPage.doLogin(getEmail(3,connectionPool), getPassword(3,connectionPool));
    }

    @Test
    public void sendEmailTest() {
        mailPage.createAnEmail(getEmail(3,connectionPool), getEmail(4,connectionPool));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
