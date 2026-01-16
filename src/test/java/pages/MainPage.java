package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MainPage extends BasePage {
    private final By openMenuSelector = AppiumBy.accessibilityId("open menu");
    private final By menuItemLogInSelector = AppiumBy.accessibilityId("menu item log in");


    @Autowired
    @Lazy
    private LoginPage loginPage;

    @Autowired
    public MainPage(AppiumDriver driver) {
        super(driver);
    }
    public MainPage clickOpenMenu() {
        click(openMenuSelector);
        return this;
    }
    public LoginPage clickLogin() {
        click(menuItemLogInSelector);

        return loginPage;
    }
}
