# Amazon_eg.Tests

Selenium + TestNG automation for Amazon Egypt login flow (flat test-only layout).

## Prerequisites

- Java 21
- Maven 3.8+
- Google Chrome

## Project structure

```
src/test/java/Amazon_eg/selenium/Amazon_eg/Tests/AmazonLoginTest.java
src/test/resources/testng.xml
```

## Run tests

From the project root:

```bash
mvn test
```

In Eclipse: right-click `AmazonLoginTest.java` or `testng.xml` → **Run As** → **TestNG Test**.

## Scenario

`AmazonLoginTest` opens [amazon.eg](https://www.amazon.eg), signs in with an unregistered email (`notregistered@test.com`), and asserts the “Looks like you're new to Amazon” message appears.
