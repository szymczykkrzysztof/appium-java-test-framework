package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

@Configuration
@ComponentScan("pages")
public class TestConfig {
    @Bean(destroyMethod = "quit")
    public AppiumDriver appiumDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setAppPackage("com.saucelabs.mydemoapp.rn");
        options.setAppActivity(".MainActivity");
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setNewCommandTimeout(Duration.ofSeconds(90));
        options.setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(60));
        URL appiumServerUrl = URI.create("http://127.0.0.1:4723/").toURL();
        AndroidDriver driver = new AndroidDriver(appiumServerUrl, options);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        options.setNoReset(true);
        return driver;
    }
}
