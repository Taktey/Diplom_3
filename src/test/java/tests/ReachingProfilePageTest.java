package tests;

import TestTools.HTTPClient;
import TestTools.TestData;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

public class ReachingProfilePageTest {
    TestData testData;
    HTTPClient httpClient;
    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Before
    public void initializationTestData() {
        testData = new TestData();
        httpClient = new HTTPClient(testData);
        System.setProperty("selenide.browser", "Chrome");
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        accountPage = page(AccountPage.class);
        httpClient.doCreateUserRequest();
        open(testData.getMainPageURL(), MainPage.class);
    }

    @After
    public void deleteTestData() {
        httpClient.doDeleteUserRequest();
    }

    @Test
    @DisplayName("Тест перехода на страницу профиля авторизированного пользователя")
    @Description("Авторизированный пользователя может перейти на страницу профиля")
    public void isReachingProfilePagePossibleTest() {
        mainPage.doLoginButtonClick();
        loginPage.loginUsingTestData(testData);
        mainPage.doAccountButtonClick();
        Boolean expected = true;
        assertEquals(expected, accountPage.isProfileTabButtonVisible());
        String actualURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedURL = testData.getAccountPageURL();
        assertEquals(expectedURL, actualURL);
    }
}
