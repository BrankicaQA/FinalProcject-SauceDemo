**SauceDemo Automation Testing Project**

**Overview**

This project contains automated tests for the SauceDemo web application using **Java, Selenium WebDriver, TestNG,** and the P**age Object Model (POM)** design pattern.

The goal of the project was to simulate a real-world QA automation framework while covering core application functionalities such as authentication, inventory management, cart operations, checkout process, product details validation, navigation testing, compatibility testing, and selected non-functional scenarios.

**Technologies Used**

- Java
- Selenium WebDriver
- TestNG
- Maven
- Apache POI
- WebDriverManager
- Firefox Driver
- Chrome Driver
- Git & GitHub

**Design Pattern**

The framework is built using the **Page Object Model (POM)** design pattern.

Each page of the application is represented by a dedicated Java class containing:
- Web element locators
- Page-specific actions
- Reusable methods

This approach improves:
- Maintainability
- Readability
- Reusability
- Scalability

**Project Structure**

src
├── main
│
└── test
    ├── Base
    │   ├── BaseTest.java
    │   └── ExcelReader.java
    │
    ├── Pages
    │   ├── LoginPage.java
    │   ├── InventoryPage.java
    │   ├── CartPage.java
    │   ├── CheckoutPage.java
    │   ├── OverviewPage.java
    │   ├── ProductDetailPage.java
    │   └── NavigationPage.java
    │
    └── Test
        ├── TestLogin.java
        ├── InventoryTest.java
        ├── CartTest.java
        ├── CheckoutTest.java
        ├── OverviewTest.java
        ├── ProductDetailTest.java
        ├── NavigationTest.java
        ├── CompatibilityTest.java
        └── NonFunctionalTests.java

**Implemented Test Scenarios**

**Login Testing**
- Successful login
- Login with empty password
- Data-driven login testing using Excel
- Validation of locked-out user behavior
**Inventory Testing**
- Product visibility verification
- Adding a single product to cart
- Adding multiple products to cart
- Removing products from cart
**Cart Testing**
- Product presence validation
- Product quantity verification
- Product removal
- Continue Shopping functionality
- Checkout navigation
**Checkout Testing**
- Successful checkout process
- Empty form validation
- Empty first name validation
- Empty last name validation
- Empty postal code validation
- Checkout attempt with empty cart
**Product Details Testing**
- Product information consistency validation
- Product image validation
- Add/Remove button functionality
- Product detail page navigation
**Checkout Overview Testing**
- Payment information validation
- Shipping information validation
- Price summary validation
- Cancel button functionality
- Finish button functionality
**Navigation Testing**
- Burger menu opening
- Burger menu closing
- Logout functionality
- About page navigation
- All Items navigation
- Reset App State functionality
- Navigation options visibility
**Compatibility Testing**
- Login validation in Chrome browser
**Non-Functional Testing**
- Rapid cart interaction validation
- SQL Injection login attempt validation

**Data-Driven Testing**

The project uses **Apache POI **for reading test data from an Excel file.

User credentials are stored externally in:

AllUsers.xlsx

Benefits:

- Separation of test data from code
- Easier maintenance
- Reusable test logic
- Better scalability
- Browser Support

Currently tested on:

- Mozilla Firefox
- Google Chrome

**Notable Features:**

**Explicit and Implicit Waits**
The framework utilizes:
- WebDriverWait (Explicit Wait)
- Implicit Wait
to improve stability and reduce flaky test behavior.

**JavaScript Executor**

JavaScriptExecutor is used when standard Selenium interactions are insufficient, such as:
- Scrolling elements into view
- Clicking hidden or difficult-to-access elements

**Stream API Usage**

Java Stream API is used for:
- Searching products inside the cart
- Simplifying collection handling
- Improving code readability

**Known Issues / Bugs Found**

During manual and automated testing several application defects were identified:

**Reset App State Bug**

After selecting Reset App State, the shopping cart badge is cleared, but the product button remains in the Remove state instead of returning to Add to cart.

**Checkout With Empty Cart**

Checkout flow allows navigation even when cart state is inconsistent after item removal.

**How to Run**

Clone the repository:
git clone https://github.com/BrankicaQA/FinalProcject-SauceDemo.git

Navigate to the project:
cd FinalProcject-SauceDemo

Run tests:
mvn test


**Author: Brankica Rajković**
QA Automation Testing Project created as part of practical learning and hands-on automation testing experience using Selenium WebDriver and TestNG.

