package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MailPage {
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

    public void doLogin(String login, String password) {
        wait.until(elementToBeClickable(submitBtn));
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        submitBtn.click();
        wait.until(visibilityOfElementLocated(By.id("PH_logoutLink")));
    }

    public void createAnEmail(String email_1, String email_2) {
        writeAnEmailBtn.click();
        wait.until(visibilityOf(sendBtn));
        toWhomField.sendKeys(email_1," ",email_2);
        topic = "Topic";
        topicField.sendKeys(topic);
        bodyField.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
        sendBtn.click();
        wait.until(invisibilityOfElementLocated(By.xpath(".//span[@data-title-shortcut='Ctrl+Enter']")));
        if(closePopupBtn.isEnabled()) {
            closePopupBtn.click();
        }
    }
}
