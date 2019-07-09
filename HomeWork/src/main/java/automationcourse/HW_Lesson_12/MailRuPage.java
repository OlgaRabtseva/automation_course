package automationcourse.HW_Lesson_12;

import automationcourse.Utils.RandomStringGeneratorUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MailRuPage {
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

    @FindBy(xpath = ".//a[@href='/inbox/']")
    public WebElement inboxFolder;

    @FindBy(xpath = ".//a[@href='/spam/']")
    public WebElement spamFolder;

    @FindBy(xpath = ".//a[@href='/sent/']")
    public WebElement sentFolder;

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

    @FindBy(className = "ll-fs_is-active")
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

    public void doLogin() {
        if(loginField.isDisplayed()) {
            wait.until(elementToBeClickable(submitBtn));
            loginField.sendKeys(LOGIN);
            passwordField.sendKeys(PASSWORD);
            submitBtn.click();
            wait.until(visibilityOfElementLocated(By.id("PH_logoutLink")));
        }
    }

    public void createAnEmail(String email_1, String email_2) {
        writeAnEmailBtn.click();
        wait.until(visibilityOf(sendBtn));
        toWhomField.sendKeys(email_1," ",email_2);
        topic = RandomStringGeneratorUtil.randomChars(7);
        topicField.sendKeys(topic);
        bodyField.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
        sendBtn.click();
        wait.until(invisibilityOfElementLocated(By.xpath(".//span[@data-title-shortcut='Ctrl+Enter']")));
        if(closePopupBtn.isEnabled()) {
            closePopupBtn.click();
        }
    }

    public void moveEmailToSpam() {
        driver.navigate().refresh();
        wait.until(visibilityOfElementLocated(By.className("llc__subject")));
        WebElement el = emailListElement.findElement(By.className("llc__subject"));
        topic = el.getText();
        el.click();
        wait.until(visibilityOfElementLocated(By.className("button2_reply")));
        spamBtn.click();
    }

    public void moveEmailFromSpam() {
        driver.navigate().refresh();
        wait.until(visibilityOf(spamFolder));
        spamFolder.click();
        wait.until(visibilityOfElementLocated(By.className("llc__subject")));
        WebElement el = emailListElement.findElement(By.className("llc__subject"));
        topic = el.getText();
        el.click();
        wait.until(visibilityOfElementLocated(By.className("button2_reply")));
        wait.until(visibilityOf(notSpamBtn));
        notSpamBtn.click();
    }

    public void markEmail(int count) {
        driver.navigate().refresh();
        wait.until(visibilityOf(inboxFolder));
        inboxFolder.click();
        wait.until(visibilityOf(inboxFolder));
        while (count > 0) {
            inactiveFlagBtn.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }
    }

    public void unmarkEmails() {
        driver.navigate().refresh();
        wait.until(visibilityOf(inboxFolder));
        inboxFolder.click();
        wait.until(visibilityOf(inboxFolder));
        for (WebElement el : activeFlagBtn) {
            wait.until(elementToBeClickable(el));
            el.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wait.until(elementToBeClickable(el));
        }
    }

    public WebElement openFolderAndFindElement(WebElement folder) {
        driver.navigate().refresh();
        wait.until(visibilityOf(folder));
        folder.click();
        wait.until(visibilityOf(emailListElement));
        return emailListElement.findElement(By.partialLinkText(topic));
    }

    public boolean openFolderAndCheckElementNotExist(WebElement folder) {
        driver.navigate().refresh();
        wait.until(visibilityOf(folder));
        folder.click();
        wait.until(visibilityOf(emailListElement));
        return emailListElement.findElements(By.partialLinkText(topic)).isEmpty();
    }

    public int amountOfMarkedEmails() {
        driver.navigate().refresh();
        wait.until(visibilityOf(inboxFolder));
        inboxFolder.click();
        return activeFlagBtn.size();
    }

    public boolean checkThatEmailsNotMarked() {
        driver.navigate().refresh();
        wait.until(visibilityOf(inboxFolder));
        inboxFolder.click();
        wait.until(visibilityOf(inboxFolder));
        return activeFlagBtn.size() == 0;
    }
}
