package pages;

import Utils.Email;
import Utils.RandomStringGeneratorUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MailRuPage {
    private static final Logger logger = Logger.getLogger(MailRuPage.class);
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

    @FindBy(xpath = ".//div[@class='new-folder-btn__button-wrapper']//*[@class='button2__txt']")
    private WebElement newFolderBtn;

    @FindBy(xpath = ".//input[@data-test-id='name']")
    private WebElement newFolderName;

    @FindBy(xpath = ".//button[@data-test-id='submit']")
    private WebElement submitNewFolderBtn;

    @FindBy(xpath = ".//a[@href='/1/']")
    public WebElement newFolder;

    @FindBy(xpath = ".//div[@data-qa-id='delete']")
    private WebElement deleteFolderContextBtn;

    @FindBy(xpath = ".//*[@class='button2__wrapper']//span[contains(text(), 'Удалить')]")
    private WebElement deleteFolderModalBtn;


    public MailRuPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
        logger.info("MailRu page is created");
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

    public void createAnEmail(String email_1, String email_2, Email email) {
        writeAnEmailBtn.click();
        wait.until(visibilityOf(sendBtn));
        toWhomField.sendKeys(email_1," ",email_2);
        topic = RandomStringGeneratorUtil.randomChars(6);
        topicField.sendKeys(topic);
        bodyField.sendKeys(email.getBody());
        sendBtn.click();
        wait.until(invisibilityOfElementLocated(By.xpath(".//span[@data-title-shortcut='Ctrl+Enter']")));
        if(closePopupBtn.isEnabled()) {
            closePopupBtn.click();
            logger.info("Popup is enabled");
        }
    }

    public void createNewFolder(String folderName) {
        newFolderBtn.click();
        wait.until(visibilityOf(submitNewFolderBtn));
        newFolderName.sendKeys(folderName);
        submitNewFolderBtn.click();
    }

    public void deleteFolder(String folderName) {
        WebElement el = driver.findElement(By.xpath(String.format(".//*[contains(text(),'%s')]", folderName)));
        Actions action = new Actions(driver);
        action.contextClick(el).perform();
        deleteFolderContextBtn.click();
        wait.until(visibilityOf(deleteFolderModalBtn));
        deleteFolderModalBtn.click();
        inboxFolder.click();
    }

    public boolean checkFolder(String folderName) {
        wait.until(visibilityOfElementLocated(By.xpath(String.format(".//*[contains(text(),'%s')]", folderName))));
        WebElement el = driver.findElement(By.xpath(String.format(".//*[contains(text(),'%s')]", folderName)));
        return el.isDisplayed();
    }

    public void checkFolderNotExist(String folderName) {
        wait.until(invisibilityOfElementLocated(By.xpath(String.format(".//*[contains(text(),'%s')]", folderName))));
        try {
            driver.findElement(By.xpath(String.format(".//*[contains(text(),'%s')]", folderName)));
            throw new RuntimeException("");
        } catch (NoSuchElementException e) {
            logger.info("Folder deleted");
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        folder.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
