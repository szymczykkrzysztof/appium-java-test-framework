package tests;

import config.TestConfig;
import model.TestUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pages.LoginPage;
import pages.MainPage;

import static constants.ContentStrings.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LoginTests {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        mainPage.clickOpenMenu().clickLogin();
    }

    @Test
    public void userCanLoginWithGoodCredentials() {
        loginPage
                .loginAs(TestUsers.BOB)
                .verifyErrorMessageIsDisplayed(false);
    }

    @Test
    public void userCannotLoginWithLockedAccount() {
        loginPage
                .loginAs(TestUsers.ALICE_LOCKED)
                .verifyErrorMessageIsDisplayed(true)
                .verifyErrorMessageContains(MSG_LOCKED_OUT);
    }

    @Test
    public void userCannotLoginWithBadCredentials() {
        loginPage
                .loginAs(TestUsers.INVALID_USER)
                .verifyErrorMessageIsDisplayed(true)
                .verifyErrorMessageContains(ERR_MSG_CREDENTIALS_MISMATCH);
    }

    @Test
    public void userCannotLoginWithFakeCredentials() {
        loginPage
                .loginAs(TestUsers.FAKE_USER)
                .verifyErrorMessageIsDisplayed(true)
                .verifyErrorMessageContains(ERR_MSG_CREDENTIALS_MISMATCH);
    }

    @Test
    public void userCannotLoginWithEmptyLogin() {
        loginPage
                .loginAs(TestUsers.EMPTY_USER)
                .verifyUserNameValidationErrorMessageIsDisplayed(true)
                .verifyLoginErrorMessage(ERR_MSG_USERNAME_REQUIRED);
    }

    @Test
    public void userCannotLoginWithEmptyPassword() {
        loginPage
                .loginAs(TestUsers.EMPTY_PASSWORD)
                .verifyPasswordValidationErrorMessageIsDisplayed(true)
                .verifyPasswordErrorMessage(ERR_MSG_PASSWORD_REQUIRED);
    }

    @Test
    public void userCanLoginAndLogout() {
        loginPage
                .loginAs(TestUsers.BOB)
                .verifyErrorMessageIsDisplayed(false)
                .navigateToMainPage()
                .clickOpenMenu()
                .clickLogout()
                .verifyLogoutMessage(MSG_LOGOUT_CONFIRMATION)
                .clickLogout()
                .verifyLogoutConfirmationPanelIsDisplayed()
                .clickLogoutConfirmationOkButton()
                .verifyLoginHeaderIsDisplayed();
    }


}
