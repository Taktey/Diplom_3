package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class AccountPage {
    @FindBy(how= How.XPATH, using = "//a[text()='Профиль']")
    public SelenideElement profileTabButton;
    @Step("Проверить видимость переключателя вкладок 'Профиль'")
    public boolean isProfileTabButtonVisible(){
        profileTabButton.shouldBe(visible);
        return profileTabButton.isDisplayed();
    }
    @FindBy(how = How.XPATH,using = "//p[text()='Конструктор']")
    public SelenideElement constructorButton;
    @Step("Нажать клавишу 'Конструктор'")
    public void doConstructorButtonClick(){
        constructorButton.click();
    }
    @FindBy(how = How.XPATH, using = "//div[@class='AppHeader_header__logo__2D0X2']/a")
    public SelenideElement headerLogo;
    @Step("Нажать клавишу на логотип")
    public void doHeaderLogoClick(){
        headerLogo.click();
    }
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    public SelenideElement exitButton;
    @Step("Проверка видимости клавиши 'Выход'")
    public AccountPage isExitButtonVisible(){
        exitButton.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
    public void doExitButtonClick(){
        exitButton.click();
    }
}
