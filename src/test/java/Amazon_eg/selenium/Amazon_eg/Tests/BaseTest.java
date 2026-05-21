package Amazon_eg.selenium.Amazon_eg.Tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

    protected static final String AMAZON_EG_URL = "https://www.amazon.eg";
    protected static final Duration WAIT_TIMEOUT = Duration.ofSeconds(15);

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        driver.manage().window().maximize();
        driver.navigate().to(AMAZON_EG_URL);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
