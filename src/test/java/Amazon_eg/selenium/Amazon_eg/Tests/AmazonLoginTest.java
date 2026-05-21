package Amazon_eg.selenium.Amazon_eg.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonLoginTest extends BaseTest {

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
}
