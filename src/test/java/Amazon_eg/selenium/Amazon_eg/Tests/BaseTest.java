package Amazon_eg.selenium.Amazon_eg.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

    protected static final String AMAZON_EG_URL = "https://www.amazon.eg";

    protected static final Duration WAIT_TIMEOUT =
            Duration.ofSeconds(15);

    protected WebDriver driver;

    protected WebDriverWait wait;


    // ==========================================
    // Setup
    // ==========================================

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, WAIT_TIMEOUT);

        driver.manage().window().maximize();

        driver.navigate().to(AMAZON_EG_URL);
    }


    // ==========================================
    // Full Page Multi Screenshot Method
    // ==========================================

    public void takeScreenshot(String screenshotName)
            throws IOException {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        // Create screenshots folder if not exists
        File screenshotFolder = new File("Report/screenshots");

        if (!screenshotFolder.exists()) {

            screenshotFolder.mkdir();
        }

        // Get total page height
        long pageHeight = (long) js.executeScript(
                "return document.body.scrollHeight");

        // Get visible window height
        long windowHeight = (long) js.executeScript(
                "return window.innerHeight");

        // Start from top
        long currentScroll = 0;

        int screenshotIndex = 1;

        while (currentScroll < pageHeight) {

            // Scroll to current section
            js.executeScript(
                    "window.scrollTo(0, arguments[0]);",
                    currentScroll);

            // Wait for scroll animation
            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            // Capture screenshot
            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File destination = new File(
                    screenshotFolder
                    + "/"
                    + screenshotName
                    + "_Part_"
                    + screenshotIndex
                    + ".png");

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println(
                    "Screenshot saved: "
                    + screenshotName
                    + "_Part_"
                    + screenshotIndex);

            // Move down by one screen
            currentScroll += windowHeight;

            screenshotIndex++;
        }

        // Return to top of page
        js.executeScript("window.scrollTo(0, 0)");
    }


    // ==========================================
    // Screenshot Only On Failure + Close Browser
    // ==========================================

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result)
            throws IOException {

        // Take screenshot only if test fails
        if (ITestResult.FAILURE == result.getStatus()) {

            takeScreenshot(result.getName());
        }

        // Close browser
        if (driver != null) {

            driver.quit();
        }
    }
}