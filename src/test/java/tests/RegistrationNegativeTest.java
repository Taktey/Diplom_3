package tests;

import TestTools.HTTPClient;
import TestTools.TestData;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

public class RegistrationNegativeTest {
    TestData testData;
    HTTPClient httpClient;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountPage accountPage;
    String fiveCharacterPassword = "12345";

    @Before
    public void initializationTestData() {
        testData = new TestData();
        httpClient = new HTTPClient(testData);
        System.setProperty("selenide.browser", "Chrome");
        mainPage = open(testData.getMainPageURL(), MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        accountPage = page(AccountPage.class);
    }

    @Test
    @DisplayName("Негативный тест регистрации пользователя, пароль из 5 символов")
    @Description("Регистрация невозможна при использовании пароля менее 6 символов")
    public void isRegistrationWithFiveCharactersPasswordImpossibleTest() {
        testData.setTestPassword(fiveCharacterPassword);
        mainPage.doAccountButtonClick();
        loginPage.doRegistrationLinkClick();
        registrationPage.doFillRegistrationFields(0, testData.getTestName())
                .doFillRegistrationFields(1, testData.getTestEmail())
                .doFillRegistrationFields(2, testData.getTestPassword())
                .doRegistrationButtonClick();
        boolean expected = true;
        boolean actual = registrationPage.isInvalidPasswordErrorDisplayed();
        assertEquals(expected, actual);
    }

}
