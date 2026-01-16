package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component
public class LogoutPopupPage extends BasePage {

    private final By logoutConfirmationText = new By.ByXPath("//android.widget.TextView[@resource-id=\"android:id/message\"]");
    private final By logoutButton = new By.ByXPath("//android.widget.Button[@resource-id=\"android:id/button1\"]");
    private final By logoutConfirmationPanel = new By.ByXPath("//android.widget.LinearLayout[@resource-id=\"android:id/parentPanel\"]");
    private final By getLogoutConfirmationOkButton = new By.ByXPath("//android.widget.Button[@resource-id=\"android:id/button1\"]");

    @Autowired
    @Lazy
    private LoginPage loginPage;

    public LogoutPopupPage(AppiumDriver driver) {
        super(driver);
    }

    public String getLogoutConfirmationText() {
        return driver.findElement(logoutConfirmationText).getText();
    }

    public LogoutPopupPage verifyLogoutMessage(String message) {
        var logoutMessage = getLogoutConfirmationText();
        assertThat(logoutMessage).contains(message);
        return this;
    }

    public LogoutPopupPage clickLogout() {
        click(logoutButton);
        return this;
    }

    public LogoutPopupPage verifyLogoutConfirmationPanelIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutConfirmationPanel));
        return this;
    }

    public LoginPage clickLogoutConfirmationOkButton() {
        click(getLogoutConfirmationOkButton);
        return loginPage;
    }
}
