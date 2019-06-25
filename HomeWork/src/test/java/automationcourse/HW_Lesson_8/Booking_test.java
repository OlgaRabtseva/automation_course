package automationcourse.HW_Lesson_8;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertTrue;

public class Booking_test {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/automation_course/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
    }

    @Test
    public void certainDatesTest() {
        WebElement searchField = driver.findElement(By.id("ss"));
        searchField.sendKeys("Moscow");
        searchField.submit();
        long currentDate = Long.valueOf(driver.findElement(By.xpath(".//td[@class='c2-day c2-day-s-today']")).getAttribute("data-id"));
        long oneDayInMilliseconds = 24 * 60 * 60 * 1000;
        String firstDate = String.valueOf(currentDate + 3 * oneDayInMilliseconds);
        String secondDate = String.valueOf(currentDate + 10 * oneDayInMilliseconds);
        driver.findElement(By.xpath(".//td[@data-id='" + firstDate + "']")).click();
        driver.findElement(By.xpath(".//div[@data-mode='checkout']")).click();
        driver.findElement(By.xpath("(.//div[@class='c2-calendar'])[2]//td[@data-id='" + secondDate + "']")).click();
        driver.findElement(By.xpath(".//button[@data-sb-id='main']")).click();
        List<WebElement> hotelList = driver.findElements(By.xpath(".//div[@data-hotelid]"));
        assertTrue(hotelList.size() > 5);
    }

    @Test
    public void hotelRateTest() throws InterruptedException {
        WebElement searchField = driver.findElement(By.id("ss"));
        searchField.sendKeys("Moscow");
        searchField.submit();
        WebElement rate90CheckBox = driver.findElement(By.xpath("(.//a[@data-id='review_score-90'])[1]//div"));
        sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",rate90CheckBox);
        sleep(1000);
        rate90CheckBox.click();
        sleep(3000);
        List<WebElement> hotelsRates = driver.findElements(By.xpath(".//div[@class='bui-review-score__badge']"));
        for (WebElement rate:hotelsRates) {
            assertTrue(Double.valueOf(rate.getText()) >= 9.0);
        }
        hotelsRates.get(0).click();
        sleep(1000);
        WebElement hotelRate = driver.findElement(By.xpath("(.//div[@class='bui-review-score__badge'])[1]"));
        assertTrue(Double.valueOf(hotelRate.getText()) >= 9.0);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
