package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final AppiumDriver driver;
    protected final WebDriverWait wait;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By locator) {
        logger.info("Clicking element: {}", locator);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (TimeoutException e) {
            logger.error("Element not clickable: {}", locator);
            throw e;
        }
    }

    protected void sendKeys(By locator, String text) {
        logger.info("Typing '{}' into element: {}", text, locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        assert element != null;
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        logger.info("Getting text from element: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    protected void hideKeyboard() {
        try {
            if (driver instanceof AndroidDriver androidDriver) {
                androidDriver.hideKeyboard();
            } else if (driver instanceof IOSDriver iosDriver) {
                if (iosDriver.isKeyboardShown()) {
                    iosDriver.hideKeyboard();
                }
            }
        } catch (Exception e) {
            logger.debug("Keyboard soft hide failed or not needed (keyboard might be closed already).");
        }
    }
}