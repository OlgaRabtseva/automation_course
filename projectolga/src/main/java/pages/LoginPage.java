package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private static final Logger logger = Logger.getLogger(LoginPage.class);
    private static final int LINK_PRESENSE_TIMEOUT = 10;
    private WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement buttonEnter;

    @FindBy(xpath = "//*[@id='PH_logoutLink']")
    private WebElement logoutLink;

    @FindBy(xpath = "//*[@id='mailbox:error']")
    public WebElement logoutLinkError;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT);
        PageFactory.initElements(driver, this);
        logger.info("Login page is created");
    }

    public void enterLoginAndPass(String login, String password)
    {
        loginField.clear();
        loginField.sendKeys(login);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickEnterButton()
    {
        buttonEnter.click();
    }

    public boolean logoutLinkPresents() {
        return (wait.until(ExpectedConditions.visibilityOf(logoutLink))
                .isDisplayed());
    }

    public boolean logoutLinkErrorPresents() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(logoutLinkError)).isDisplayed();
    }
}
