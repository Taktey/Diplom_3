package pages;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
public class RegistrationPage {

    @FindBy(how = How.XPATH, using = "(//fieldset//input)")
    public ElementsCollection registrationFields;
    @Step("Заполнить поле регистрации")
    public RegistrationPage doFillRegistrationFields(int fieldOrderNumber, String filling){
        registrationFields.get(fieldOrderNumber).sendKeys(filling);
        return this;
    }// Метод использует нумерацию полей с нуля, по аналогии с массивом
    @FindBy(how= How.XPATH, using = "//button[text()='Зарегистрироваться']")
    public SelenideElement registrationButton;
    @Step("Нажать клавишу регистрации")
    public void doRegistrationButtonClick(){
        registrationButton.click();
    }
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    public SelenideElement invalidPasswordError;
    @Step("Проверить отображение сообщения о невалидном пароле")
    public boolean isInvalidPasswordErrorDisplayed(){
        return invalidPasswordError.isDisplayed();
    }
    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    public SelenideElement loginLink;
    @Step("Нажать клавишу 'Войти'")
    public void doLoginLinkClick(){
        loginLink.click();
    }
}
