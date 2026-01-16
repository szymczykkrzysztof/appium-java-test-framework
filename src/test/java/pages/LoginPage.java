package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component
public class LoginPage extends BasePage {
    private final By usernameField = AppiumBy.accessibilityId("Username input field");
    private final By passwordField = AppiumBy.accessibilityId("Password input field");
    private final By loginButton = AppiumBy.accessibilityId("Login button");
    private final By errorMessage = AppiumBy.accessibilityId("generic-error-message");
    private final By loginHeader = AppiumBy.accessibilityId("container header");
    private final By loginValidationErrorMessage = AppiumBy.accessibilityId("Username-error-message");
    private final By passwordValidationErrorMessage = AppiumBy.accessibilityId("Password-error-message");

    @Autowired
    @Lazy
    private MainPage mainPage;

    @Autowired
    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage loginAs(User user) {
        enterUsername(user.username());
        enterPassword(user.password());
        clickLogin();
        return this;
    }

    public LoginPage enterUsername(String username) {
        sendKeys(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordField, password);
        return this;
    }

    public LoginPage clickLogin() {
        click(loginButton);
        return this;
    }

    public LoginPage verifyErrorMessageIsDisplayed(boolean expectedResult) {
        assertThat(isDisplayed(errorMessage))
                .as("Provided credentials do not match any user in this service.")
                .isEqualTo(expectedResult);

        return this;
    }

    public LoginPage verifyUserNameValidationErrorMessageIsDisplayed(boolean expectedResult) {
        assertThat(isDisplayed(loginValidationErrorMessage))
                .as("Username is required")
                .isEqualTo(expectedResult);
        return this;
    }
    public LoginPage verifyPasswordValidationErrorMessageIsDisplayed(boolean expectedResult) {
        assertThat(isDisplayed(passwordValidationErrorMessage))
                .as("Password is required")
                .isEqualTo(expectedResult);
        return this;
    }

    public LoginPage verifyLoginErrorMessage(String expectedText) {
        var errorMessageText = getLoginValidationErrorMessageText(loginValidationErrorMessage);
        assertThat(errorMessageText).isEqualTo(expectedText);
        return this;
    }

    public LoginPage verifyPasswordErrorMessage(String expectedText) {
        var errorMessageText = getLoginValidationErrorMessageText(passwordValidationErrorMessage);
        assertThat(errorMessageText).isEqualTo(expectedText);
        return this;
    }

    public void verifyErrorMessageContains(String expectedText) {
        var errorMessageText = getErrorMessageText();
        assertThat(errorMessageText).contains(expectedText);
    }


    public LoginPage verifyLoginHeaderIsDisplayed() {
        var containerHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
        var headerText = containerHeader.findElement(AppiumBy.className("android.widget.TextView"));
        assertThat(headerText.getText()).isEqualTo("Login");
        return this;
    }

    public MainPage navigateToMainPage() {
        return mainPage;
    }

    private String getErrorMessageText() {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        WebElement textElement = container.findElement(AppiumBy.className("android.widget.TextView"));
        return textElement.getText();
    }

    private String getLoginValidationErrorMessageText(By locator) {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement textElement = container.findElement(AppiumBy.className("android.widget.TextView"));
        return textElement.getText();
    }
}
