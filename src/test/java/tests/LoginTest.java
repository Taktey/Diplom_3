package tests;

import TestTools.HTTPClient;
import TestTools.TestData;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    TestData testData;
    HTTPClient httpClient;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PasswordRecoveryPage passwordRecoveryPage;

    @Before
    public void initializationTestData() {
        testData = new TestData();
        httpClient = new HTTPClient(testData);
        System.setProperty("selenide.browser", "Chrome");
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        passwordRecoveryPage = page(PasswordRecoveryPage.class);
        httpClient.doCreateUserRequest();
        open(testData.getMainPageURL(), MainPage.class);
    }

    @After
    public void deleteTestData() {
        httpClient.doDeleteUserRequest();
    }

    @Test
    @DisplayName("Тест авторизации пользователя с использованием кнопки на главной странице")
    @Description("Авторизация через кнопку входа на главной странице возможна")
    public void isLoginWithMainPageLoginButtonPossibleTest() {
        mainPage.isLoginButtonVisible()
                .doLoginButtonClick();
        loginPage.loginUsingTestData(testData);
        mainPage.isOrderButtonVisible();
        boolean expected = true;
        boolean actual = mainPage.isOrderButtonDisplayed();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест авторизации пользователя с использованием клвыиши личного кабинета")
    @Description("Авторизация через клавишу перехода в личный кабинет возможна")
    public void isLoginWithMainPageAccountButtonPossibleTest() {
        mainPage.isLoginButtonVisible()
                .doLoginButtonClick();
        loginPage.loginUsingTestData(testData);
        mainPage.isOrderButtonVisible();
        boolean expected = true;
        boolean actual = mainPage.isOrderButtonDisplayed();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест авторизации пользователя с использованием ссылки на странице регистрации")
    @Description("Авторизация через ссылку на странице регистрации возможна")
    public void isLoginWithRegistrationPageLoginLinkPossibleTest() {
        mainPage.isLoginButtonVisible()
                .doLoginButtonClick();
        loginPage.doRegistrationLinkClick();
        registrationPage.doLoginLinkClick();
        loginPage.loginUsingTestData(testData);
        mainPage.isOrderButtonVisible();
        boolean expected = true;
        boolean actual = mainPage.isOrderButtonDisplayed();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест авторизации пользователя с использованием ссылки на странице восстановления пароля")
    @Description("Авторизация через ссылку на странице восстановления пароля возможна")
    public void isLoginWithPasswordRecoveryPageLoginButtonPossibleTest() {
        mainPage.isLoginButtonVisible()
                .doLoginButtonClick();
        loginPage.doPasswordRecoveryLinkClick();
        passwordRecoveryPage.doLoginLinkClick();
        loginPage.loginUsingTestData(testData);
        mainPage.isOrderButtonVisible();
        boolean expected = true;
        boolean actual = mainPage.isOrderButtonDisplayed();
        assertEquals(expected, actual);
    }
}
