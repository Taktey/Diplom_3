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

public class AccountPageToConstructorPathTest {
    TestData testData;
    HTTPClient httpClient;
    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;
    PasswordRecoveryPage passwordRecoveryPage;

    @Before
    public void initializationTestData() {
        testData = new TestData();
        httpClient = new HTTPClient(testData);
        System.setProperty("selenide.browser", "Chrome");
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        passwordRecoveryPage = page(PasswordRecoveryPage.class);
        accountPage = page(AccountPage.class);
        httpClient.doCreateUserRequest();
        open(testData.getMainPageURL(), MainPage.class);
        mainPage.doLoginButtonClick();
        loginPage.loginUsingTestData(testData);
        mainPage.doAccountButtonClick();
    }

    @After
    public void deleteTestData() {
        httpClient.doDeleteUserRequest();
    }

    @Test
    @DisplayName("Тест перехода на страницу конструктора по кнопке 'Конструктор'")
    @Description("Нажатие кнопки 'Конструктор' приводит к переходу на страницу с конструктором")
    public void isConstructorReachableViaConstructorButtonTest() {
        accountPage.doConstructorButtonClick();
        String actualURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedURL = testData.getMainPageURL();
        assertEquals(expectedURL, actualURL);
    }

    @Test
    @DisplayName("Тест перехода на страницу конструктора при нажатии на логотип")
    @Description("Нажатие на логотип приводит к переходу на страницу с конструктором")
    public void isConstructorReachableViaHeaderLogoButtonTest() {
        accountPage.doHeaderLogoClick();
        String actualURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedURL = testData.getMainPageURL();
        assertEquals(expectedURL, actualURL);
    }

}
