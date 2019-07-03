package automationcourse.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MailRuPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "compose-button__txt")
    private WebElement writeAnEmailBtn;

    @FindBy(xpath = ".//a[@href='/inbox/']")
    private WebElement inboxFolder;

    @FindBy(xpath = ".//a[@href='/spam/']")
    private WebElement spamFolder;

    @FindBy(xpath = ".//div[@data-type='to']//input")
    private WebElement toWhomField;

    @FindBy(xpath = ".//input[@tabindex='400']")
    private WebElement topicField;

    @FindBy(xpath = ".//div[@tabindex='505']")
    private WebElement bodyField;

    @FindBy(xpath = ".//span[@data-title-shortcut='Ctrl+Enter']")
    private WebElement sendBtn;

    @FindBy(xpath = ".//button[@title='Пометить флажком']")
    private WebElement inactiveFlagBtn;

    @FindBy(xpath = ".//button[@title='Снять флажок']")
    private List<WebElement> activeFlagBtn;

    @FindBy(className = "portal-menu-element_spam")
    private WebElement spamBtn;

    @FindBy(className = "portal-menu-element_unspam")
    private WebElement notSpamBtn;

    @FindBy(className = "button2_close")
    private WebElement closePopupBtn;

    @FindBy(xpath = ".//div[@class='dataset__items']")
    private WebElement emailListElement;

    public MailRuPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void createAnEmail(String topic, String email_1, String email_2, String email_3) {
        writeAnEmailBtn.click();
        wait.until(visibilityOf(sendBtn));
        toWhomField.sendKeys(email_1," ",email_2, " ",email_3);
        topicField.sendKeys(topic);
        bodyField.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
        sendBtn.click();
        wait.until(invisibilityOfElementLocated(By.xpath(".//span[@data-title-shortcut='Ctrl+Enter']")));
        //wait.until(invisibilityOf(sendBtn));   - this is the same method as previous, but it does't work for some reason
        if(closePopupBtn.isEnabled()) {
            closePopupBtn.click();
        }
    }

    public void moveEmailToSpam(String topic) {
        wait.until(visibilityOf(emailListElement));
        wait.until(visibilityOfElementLocated(By.partialLinkText(topic)));
        WebElement el = emailListElement.findElement(By.partialLinkText(topic));
        el.click();
        wait.until(visibilityOfElementLocated(By.className("button2_reply")));
        spamBtn.click();
    }

    public void moveEmailFromSpam(String topic) {
        driver.navigate().refresh();
        wait.until(visibilityOf(spamFolder));
        spamFolder.click();
        wait.until(visibilityOfElementLocated(By.partialLinkText(topic)));
        WebElement el = emailListElement.findElement(By.partialLinkText(topic));
        el.click();
        wait.until(visibilityOfElementLocated(By.className("button2_reply")));
        notSpamBtn.click();
    }

    public void markEmail(int count) {
        driver.navigate().refresh();
        wait.until(visibilityOf(inboxFolder));
        inboxFolder.click();
        while (count > 0) {
            wait.until(visibilityOf(inactiveFlagBtn));
            inactiveFlagBtn.click();
            count--;
        }
    }

    public void unmarkEmails() {
        driver.navigate().refresh();
        wait.until(visibilityOf(inboxFolder));
        inboxFolder.click();
        for (WebElement el : activeFlagBtn) {
            el.click();
        }
    }
}
