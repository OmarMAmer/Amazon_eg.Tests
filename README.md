# Amazon_eg.Tests

Selenium + TestNG automation for Amazon Egypt (login, cart, and account-access flows; flat test-only layout).

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
  AccountAccessVerification.java
src/test/resources/testng.xml
```

## Run tests

From the project root:

```bash
mvn test
```

This runs the full suite defined in `testng.xml` (login, cart, and account-access tests).

In Eclipse: right-click `testng.xml` or an individual test class → **Run As** → **TestNG Test**.

## Test scenarios

### `AmazonLoginTest`

Opens [amazon.eg](https://www.amazon.eg), signs in with an unregistered email (`notregistered@test.com`), and asserts the “Looks like you're new to Amazon” message appears.

### `CartItemsVerification`

Opens [amazon.eg](https://www.amazon.eg), navigates to Today's Deals, adds a product to the cart with quantity 2, and verifies the cart shows the correct product name, price, quantity, and subtotal.

### `AccountAccessVerification`

Opens [amazon.eg](https://www.amazon.eg) as a guest and uses the **Account & Lists** menu (hover + link click):

| Test | What it checks |
|------|----------------|
| `verifyYourOrdersRequiresLogin` | **Your Orders** leads to a sign-in page |
| `verifyYourAddressesRequiresLogin` | **Your Addresses** leads to a sign-in page |
| `verifyYourListsAccessible` | **Your Lists** opens the lists area (page content related to lists) Note: This test case is intentionally failed to capture screenshots. |

Tests run in priority order (1 → 2 → 3) within this class.
