package automationcourse.HW_Lesson_9;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.testng.Assert.*;

public class BookingParisTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/automation_course/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
    }

    @Test
    public void cheapestHotelTest() throws InterruptedException {
        WebElement searchField = driver.findElement(By.id("ss"));
        searchField.sendKeys("Paris");
        searchField.submit();
        long currentDate = Long.valueOf(driver.findElement(By.className("c2-day-s-today")).getAttribute("data-id"));
        long oneDayInMilliseconds = 24 * 60 * 60 * 1000;

        String startDate = String.valueOf(currentDate + 3 * oneDayInMilliseconds);
        String endDate = String.valueOf(currentDate + 10 * oneDayInMilliseconds);
        driver.findElement(By.xpath(".//td[@data-id='" + startDate + "']")).click();
        driver.findElement(By.xpath(".//div[@data-mode='checkout']")).click();
        driver.findElement(By.xpath("(.//div[@class='c2-calendar'])[2]//td[@data-id='" + endDate + "']")).click();
        driver.findElement(By.xpath(".//button[@data-sb-id='main']")).click();

        WebElement cheapestHotelsCheckBox = driver.findElement(By.xpath("(.//a[@data-id='pri-2'])[1]//div"));
        cheapestHotelsCheckBox.click();
        sleep(2000);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("(.//a[@data-id='oos-1']//div)[1]")));
        driver.findElement(By.xpath("(.//a[@data-id='oos-1']//div)[1]")).click();
        sleep(3000);

        List<WebElement> hotels = driver.findElements(By.xpath(".//div[@data-hotelid]"));
        assertFalse(hotels.isEmpty());

        Integer maxPrice = getMaxPriceOfSelectedHotels(hotels);
        Integer expectedPrice = 1610;
        assertTrue(maxPrice <= expectedPrice);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("(.//div[@id='filter_review']//input)[1]")));
        WebElement higherRateCheckBox = driver.findElement(By.xpath(".//div[@id='filter_review']"));
        higherRateCheckBox.findElement(By.className("bui-checkbox__label")).click();
        sleep(3000);

        List<WebElement> hotelBestList = driver.findElements(By.xpath(".//div[@data-hotelid]"));
        Integer maxBestPrice = getMaxPriceOfSelectedHotels(hotelBestList);
        assertTrue(maxBestPrice <= expectedPrice);
    }

    @Test
    public void expensiveHotelTest() throws InterruptedException {
        WebElement searchField = driver.findElement(By.id("ss"));
        searchField.sendKeys("Paris");
        searchField.submit();
        long currentDate = Long.valueOf(driver.findElement(By.className("c2-day-s-today")).getAttribute("data-id"));
        long oneDayInMilliseconds = 24 * 60 * 60 * 1000;

        String startDate = String.valueOf(currentDate + 3 * oneDayInMilliseconds);
        String endDate = String.valueOf(currentDate + 10 * oneDayInMilliseconds);
        driver.findElement(By.xpath(".//td[@data-id='" + startDate + "']")).click();
        driver.findElement(By.xpath(".//div[@data-mode='checkout']")).click();
        driver.findElement(By.xpath("(.//div[@class='c2-calendar'])[2]//td[@data-id='" + endDate + "']")).click();
        driver.findElement(By.xpath(".//select[@id='group_adults']//option[@value='4']")).click();
        driver.findElement(By.xpath(".//select[@id='no_rooms']//option[@value='2']")).click();
        driver.findElement(By.xpath(".//button[@data-sb-id='main']")).click();
        sleep(2000);

        WebElement expensiveHotelsCheckBox = driver.findElement(By.xpath("(.//a[@data-id='pri-5'])[1]//div"));
        expensiveHotelsCheckBox.click();
        sleep(2000);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("(.//a[@data-id='oos-1']//div)[1]")));
        driver.findElement(By.xpath("(.//a[@data-id='oos-1']//div)[1]")).click();
        sleep(3000);

        driver.findElement(By.xpath(".//a[@data-category='price']")).click();
        sleep(3000);

        WebElement hotel = driver.findElement(By.xpath(".//div[@data-hotelid]"));
        Integer hotelPriceWithTaxes = Integer.valueOf(hotel.findElement(By.className("bui-price-display__value"))
                .getText().replaceAll("[A-Z,\\s]", ""));
        Integer expectedPrice = 6510;
        assertTrue(hotelPriceWithTaxes >= expectedPrice);
    }

    @Test(dependsOnMethods = "expensiveHotelTest")
    public void bookHotelTest() throws InterruptedException {
        WebElement hotel = driver.findElement(By.xpath(".//div[@data-hotelid]"));
        hotel.click();
        sleep(2000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.xpath(".//select[@class='hprt-nos-select']//option[@value='4']")).click();
        driver.findElement(By.xpath(".//button[@data-tooltip-class='submit_holder_button_tooltip']")).click();
        sleep(1000);
        driver.findElement(By.id("bp_travel_purpose_leasure")).click();
        driver.findElement(By.xpath(".//select[@id='booker_title']//option[@value='mr']")).click();
        driver.findElement(By.id("lastname")).sendKeys("Test book");
        driver.findElement(By.id("email")).sendKeys("Testbook@gmail.com");
        driver.findElement(By.id("email_confirm")).sendKeys("Testbook@gmail.com");
        driver.findElement(By.xpath("(.//button[@type='submit'])[1]")).click();

        driver.findElement(By.id("phone")).sendKeys("5555555");
        driver.findElement(By.xpath(".//select[@id='cc_type']//option[@value='Visa']")).click();
        driver.findElement(By.id("cc_number")).sendKeys("4012888888881881");
        driver.findElement(By.xpath(".//select[@id='cc_month']//option[@value='02']"));
        driver.findElement(By.xpath(".//select[@id='ccYear']//option[@value='2022']"));
        driver.findElement(By.id("cc_cvc")).sendKeys("424");
        driver.findElement(By.xpath("(.//ins[@class='submit_holder_button_text_wrap'])[2]")).click();
        sleep(4000);
        assertTrue(driver.findElement(By.xpath(".//div[@class='iam-embedded-registration-wrapper']")).isDisplayed());


        //driver.switchTo();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private Integer getMaxPriceOfSelectedHotels(List<WebElement> hotels) {
        List<Integer> hotelsPrice = new ArrayList<>();
        for (WebElement hotel : hotels) {
            Integer priceWithTaxes = Integer.valueOf(hotel.findElement(By.className("bui-price-display__value")).getText().replaceAll("[A-Z,\\s]", ""));
            String taxesStr = hotel.findElement(By.className("prd-taxes-and-fees-under-price")).getText().replaceAll("[A-Za-z,+\\s]", "");
            Integer taxes = taxesStr.isEmpty() ? 0 : Integer.valueOf(taxesStr);
            hotelsPrice.add(priceWithTaxes - taxes);

        }
        return hotelsPrice.stream().max(Comparator.naturalOrder()).orElse(0);
    }
}
