package Amazon_eg.selenium.Amazon_eg.Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonLoginTest {
	ChromeDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void openURL()
	{
		driver = new ChromeDriver();
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
		driver.manage().window().maximize();
		driver.navigate().to("https://www.amazon.eg");
	
	}
	
    @Test
    public void loginWithUnregisteredEmail() {
    	
        // Wait until Sign In button is clickable
        WebElement signInButton = wait.until(ExpectedConditions.
        		elementToBeClickable(By.id("nav-link-accountList")));
        // Click Sign In
        signInButton.click();
        
        // Wait until email field is visible
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("ap_email_login")));
        
        // Enter unregistered email
        emailField.sendKeys("notregistered@test.com");

        // Click continue
        driver.findElement(By.id("continue")).click();
        
        // Get error message        
        WebElement message = wait.until(
        	    ExpectedConditions.visibilityOfElementLocated(
        	        By.xpath("//*[@id=\"intent-confirmation-container\"]/h1")));

        String actualMessage = message.getText();

        Assert.assertEquals(actualMessage,
                "Looks like you're new to Amazon");
    }
    
    @AfterTest
    public void closeBrowser() {

        driver.quit();
    }
}
