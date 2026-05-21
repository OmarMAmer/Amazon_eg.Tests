# Amazon_eg.Tests

Selenium + TestNG automation for Amazon Egypt (login and cart flows; flat test-only layout).

## Prerequisites

- Java 21
- Maven 3.8+
- Google Chrome (ChromeDriver is resolved automatically via WebDriverManager)

## Project structure

```
src/test/java/Amazon_eg/selenium/Amazon_eg/Tests/
  BaseTest.java
  AmazonLoginTest.java
  CartItemsVerification.java
src/test/resources/testng.xml
```

## Run tests

From the project root:

```bash
mvn test
```

This runs the full suite defined in `testng.xml` (login and cart tests).

In Eclipse: right-click `testng.xml` or an individual test class → **Run As** → **TestNG Test**.

## Test scenarios

### `AmazonLoginTest`

Opens [amazon.eg](https://www.amazon.eg), signs in with an unregistered email (`notregistered@test.com`), and asserts the “Looks like you're new to Amazon” message appears.

### `CartItemsVerification`

Opens [amazon.eg](https://www.amazon.eg), navigates to Today's Deals, adds a product to the cart with quantity 2, and verifies the cart shows the correct product name, price, quantity, and subtotal.
