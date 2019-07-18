import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class MailPage {
    private final String LOGIN = "o.rya@inbox.ru";
    private final String PASSWORD = "X7013107x&";
    private String topic;

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(id = "mailbox:submit")
    private WebElement submitBtn;

    @FindBy(className = "compose-button__txt")
    private WebElement writeAnEmailBtn;

    @FindBy(xpath = ".//div[@data-type='to']//input")
    private WebElement toWhomField;

    @FindBy(xpath = ".//input[@tabindex='400']")
    private WebElement topicField;

    @FindBy(xpath = ".//div[@tabindex='505']")
    private WebElement bodyField;

    @FindBy(xpath = ".//span[@data-title-shortcut='Ctrl+Enter']")
    private WebElement sendBtn;

    @FindBy(className = "button2_close")
    private WebElement closePopupBtn;

    public MailPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void doLogin() {
        wait.until(elementToBeClickable(submitBtn));
        loginField.sendKeys(LOGIN);
        passwordField.sendKeys(PASSWORD);
        submitBtn.click();
        wait.until(visibilityOfElementLocated(By.id("PH_logoutLink")));
    }

    public void openNewEmail(String email_1) {
        writeAnEmailBtn.click();
        wait.until(visibilityOf(sendBtn));
        toWhomField.sendKeys(email_1);
    }

    public void setTopic() {
        topic = "Topic";
        topicField.sendKeys(topic);
    }

    public void setBody() {
        bodyField.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
    }

    public void sendEmail() {
        sendBtn.click();
        wait.until(invisibilityOfElementLocated(By.xpath(".//span[@data-title-shortcut='Ctrl+Enter']")));
        if(closePopupBtn.isEnabled()) {
            closePopupBtn.click();
        }
    }
}
