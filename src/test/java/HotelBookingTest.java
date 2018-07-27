import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HotelBookingTest {

    WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {
        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        List<WebElement> locationOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        locationOptions.get(1).click();

        driver.findElement(By.xpath("//a[contains(@class,'ui-state-active')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(@class,'ui-state-active')]")).click();

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        driver.quit();

    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


    public HotelBookingTest() {
        PageFactory.initElements(driver, this);
    }
}
