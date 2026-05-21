package Amazon_eg.selenium.Amazon_eg.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountAccessVerification extends BaseTest {
  
    // ==========================================
    // Verify Your Orders
    // ==========================================

    @Test (priority = 1)
    public void verifyYourOrdersRequiresLogin() {

        // Hover on Account & Lists
        WebElement accountLists = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("nav-link-accountList")));

        Actions actions = new Actions(driver);

        actions.moveToElement(accountLists).perform();

        // Click Your Orders
        WebElement yourOrders = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Your Orders")));

        yourOrders.click();

        // Verify sign in page appears
        WebElement ordersSignInHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Sign in')]")));

        Assert.assertTrue(ordersSignInHeader.isDisplayed());
    }

    
    // ==========================================
    // Verify Your Addresses
    // ==========================================

    @Test (priority = 2)
    public void verifyYourAddressesRequiresLogin() {

        // Hover on Account & Lists
        WebElement accountLists = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("nav-link-accountList")));

        Actions actions = new Actions(driver);

        actions.moveToElement(accountLists).perform();

        // Click Your Addresses
        WebElement yourAddresses = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Your Addresses")));

        yourAddresses.click();

        // Verify sign in page appears
        WebElement addressSignInHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Sign in')]")));

        Assert.assertTrue(addressSignInHeader.isDisplayed());
    }

    
    // ==========================================
    // Verify Your Lists
    // ==========================================

    @Test (priority = 3)
    public void verifyYourListsAccessible() {

        // Hover on Account & Lists
        WebElement accountLists = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("nav-link-accountList")));

        Actions actions = new Actions(driver);

        actions.moveToElement(accountLists).perform();

        // Click Your Lists
        WebElement yourLists = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Your Lists")));

        yourLists.click();

        // Intentionally failing assertion for screenshot testing
        WebElement fakeSignInHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Sign in')]")));

        Assert.assertTrue(fakeSignInHeader.isDisplayed());

        // Actual correct assertion, Dead Code
        WebElement listsHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Lists')]")));

        Assert.assertTrue(listsHeader.isDisplayed());
    }
}