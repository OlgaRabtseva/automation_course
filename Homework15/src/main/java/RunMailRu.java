import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.exceptions.TestFailedException;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunMailRu {
    public static void main(String[] args) {
        String email = "o.rya@inbox.ru";

        System.setProperty("webdriver.chrome.driver", "D:/automation_course/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        Eyes eyes = new Eyes();

        eyes.setApiKey("sdCFLEwSoIaAt9Es98y8JqfGIVHIW6m9APxge3nnneEk110");

        try {
            eyes.open(driver, "appName","windowName", new RectangleSize(1300, 900));

            driver.get("https://mail.ru");

            eyes.checkWindow("Login view");

            MailPage mailPage = new MailPage(driver);
            mailPage.doLogin();

            eyes.checkWindow("Mail view");

            mailPage.openNewEmail(email);

            eyes.checkWindow("Open new email view");

            mailPage.setTopic();
            eyes.checkElement(By.xpath(".//*[@class='compose-app__compose']"));

            mailPage.setBody();
            eyes.checkElement(By.xpath(".//*[@class='compose-app__buttons']"));

            mailPage.sendEmail();
            eyes.checkWindow("Final view");

            eyes.close();

        } catch (TestFailedException e) {
            System.out.println("\n" + e + "\n");
        } finally {

            driver.quit();

            eyes.abortIfNotClosed();

            System.exit(0);
        }

    }
}
