📱 Appium Java Spring Framework
A robust, scalable, and modern Mobile Test Automation Framework built for Android (and iOS) using Appium and Java 21. This project demonstrates an enterprise-grade architecture leveraging Spring Boot for Dependency Injection and state management.

The framework is designed to test the Sauce Labs My Demo App (React Native).

🚀 Key Features
Spring Dependency Injection (IOC): Uses Spring Context to manage the lifecycle of the AppiumDriver and Page Objects. No manual object instantiation (new) in tests.

Page Object Model (POM): Clean separation between test logic and UI element locators.

Fluent API: Methods return Page Objects, allowing for readable, chainable test steps (e.g., login().enterPassword().clickSubmit()).

Singleton Driver per Test: Ensures a fresh driver instance for every test method using @DirtiesContext.

Robust Wait Strategies: Implements explicit waits in a base class to handle synchronization issues and flaky tests.

Cross-Platform Ready: Architecture supports easy extension to iOS (XCUITest).

🛠️ Tech Stack
Language: Java 21

Core Tool: Appium Java Client 9.x

DI Container: Spring Boot Test (Spring Framework 6)

Test Runner: JUnit 5 (Jupiter)

Build Tool: Maven

Assertion Library: AssertJ (Fluent Assertions)

Platform: Android (UiAutomator2)
🏎️ How to Run
Prerequisites:

Java JDK 21 installed.

Appium Server running (npm install -g appium).

Android Emulator running (Android 14 / API 34 recommended).

The Sauce Labs Demo App APK installed on the emulator.

Clone the repository:

Bash

git clone https://github.com/YOUR_USERNAME/appium-java-test-framework.git
cd appium-java-test-framework
Run tests using Maven:

Bash

mvn clean test
📝 Example Test Code
The framework allows writing clean, readable tests:

Java

@Test
public void successfulLoginTest() {
    loginPage
        .enterUsername("bob@example.com")
        .enterPassword("secret")
        .clickLogin()
        .verifyLoginSuccess();
}
