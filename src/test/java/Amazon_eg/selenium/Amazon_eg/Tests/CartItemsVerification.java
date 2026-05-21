package Amazon_eg.selenium.Amazon_eg.Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartItemsVerification extends BaseTest {

    String productName;
    String productPrice;

    @Test
    public void addItemToCart() throws InterruptedException {

        // Go to Today's Deals
        WebElement todaysDeals = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Today's Deals")));

        todaysDeals.click();
                
        // Click 2nd category
        WebElement secondCategory = wait.until(
        	    ExpectedConditions.elementToBeClickable(
        	        By.xpath("//*[@id=\"discount-bubble-discounts-collection-deals-ending-today\"]")));
        
        secondCategory.click();
        
        WebElement firstProduct = wait.until(
        	    ExpectedConditions.elementToBeClickable(
        	        By.xpath("//*[@id=\"DealsGridScrollAnchor\"]"
        	        		+ "/div[3]/div/div/div[2]/div[1]/div/div/div[1]/div[2]/a")));
        firstProduct.click();
        
        // Save product name
        WebElement productTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("productTitle")));

        productName = productTitle.getText();
        System.out.println(productName);

        // Save product price
        WebElement priceElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("a-price-whole")));

        productPrice = priceElement.getText();
        System.out.println(productPrice);

        
        // Change quantity to 2
        WebElement quantityDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("quantity")));

        Select select = new Select(quantityDropdown);

        select.selectByVisibleText("2");
        
        Thread.sleep(1000); 
        
        // Add to cart
        WebElement addToCartButton = wait.until(
        		ExpectedConditions.elementToBeClickable(
        				By.id("add-to-cart-button")));
        
        addToCartButton.click();
        
        try {

            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // Close Add to your order window
            WebElement closeAddtoYourOrder = shortWait.until(
            		ExpectedConditions.elementToBeClickable(
            				By.id("attach-warranty-close-icon")));

        closeAddtoYourOrder.click();
        
        } 
        
        catch (Exception e) 
        {
        	System.out.println("Popup did not appear");
        }
        
        Thread.sleep(3000);
        

        // Go to cart
        WebElement cartButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("nav-cart")));
        cartButton.click();
        
        // Verify product name
        WebElement cartProductName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("a-truncate-cut")));

        Assert.assertTrue(
                cartProductName.getText().contains(productName));
        
        // Verify product price
        WebElement cartPrice = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("sc-product-price")));

        String cartProductPrice = cartPrice.getText();

        System.out.println("Original Product Price: " + productPrice);
        System.out.println("Cart Product Price: " + cartProductPrice);

        // Clean prices
        String cleanOriginalPrice = productPrice.replaceAll("[^0-9]", "");

        String cleanCartPrice = cartProductPrice.replaceAll("[^0-9]", "");

        // Verify prices match
        Assert.assertTrue(
                cleanCartPrice.contains(cleanOriginalPrice));
        
        

        // Verify quantity = 2
        WebElement quantityValue = driver.findElement(
                By.id("sc-subtotal-label-activecart"));

        Assert.assertTrue(quantityValue.getText().contains("2"));
        
        // Verify subtotal = price × quantity

        // Convert original product price to number
        double originalPrice = Double.parseDouble(
        		productPrice.replaceAll("[^0-9.]", ""));

        // Expected subtotal
        double expectedSubtotal = originalPrice * 2;

        System.out.println("Expected Subtotal: " + expectedSubtotal);

        // Get subtotal text from cart
        WebElement subtotalElement = wait.until(
        		ExpectedConditions.visibilityOfElementLocated(
        				By.id("sc-subtotal-amount-activecart")));

        String subtotalText = subtotalElement.getText();

        System.out.println("Actual Subtotal: " + subtotalText);

        // Clean subtotal text
        String cleanSubtotal = subtotalText.replaceAll("[^0-9.]", "");

        // Convert subtotal to number
        double actualSubtotal = Double.parseDouble(cleanSubtotal);

        // Verify subtotal
        Assert.assertEquals(actualSubtotal, expectedSubtotal);
    }
}
