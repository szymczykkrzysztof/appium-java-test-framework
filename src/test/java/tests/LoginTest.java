package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pages.LoginPage;
import pages.MainPage;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LoginTest {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        mainPage.clickOpenMenu().clickLogin();
    }

    @Test
    public void springLoginTest() {
        loginPage
                .enterUsername("bob@example.com")
                .enterPassword("10203040")
                .clickLogin();
    }
}
