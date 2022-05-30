package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class MainPage {

    @FindBy(how = How.XPATH, using = "//div[@class='AppHeader_header__logo__2D0X2']")
    public SelenideElement logoBlock;
    @FindBy(how = How.XPATH, using = "//nav/a[@class='AppHeader_header__link__3D_hX']")
    public SelenideElement accountButton;
    @Step("Дождаться видимости клавиши 'Личный кабинет'")
    public MainPage isAccountButtonVisible() {
        accountButton.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    public void doAccountButtonClick() {
        accountButton.click();
    }

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    public SelenideElement loginButton;
    @Step("Нажать клавишу 'Войти'")
    public void doLoginButtonClick() {
        loginButton.click();
    }
    @Step("Дождаться видимости клавиши 'Войти'")
    public MainPage isLoginButtonVisible() {
        loginButton.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    public SelenideElement orderButton;
    @Step("Дождаться видимости клавиши 'Оформить заказ'")
    public MainPage isOrderButtonVisible() {
        orderButton.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
    @Step("Проверить видимость клавиши 'Оформить заказ'")
    public boolean isOrderButtonDisplayed() {
        return orderButton.isDisplayed();
    }
    @FindBy(how = How.XPATH, using = "(//span[@class='text text_type_main-default'])")
    public ElementsCollection ingredientTabs;
    @Step("дождаться видимости переключателя вкладки ингредиентов и нажать на вкладку")
    public void doIngredientTabClick(int elementNumber){
        ingredientTabs.get(elementNumber)
                .shouldBe(visible,Duration.ofSeconds(10)).shouldBe(visible).click();
    }
    @FindBy(how = How.XPATH, using = "(//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2)")
    public ElementsCollection ingredientCategoriesHeaders;
    @Step("Проверить видимость клавиши переключателей категорий ингредиентов")
    public boolean isIngredientCategoriesHeaderVisible(int categoryNumber){
        return ingredientCategoriesHeaders.get(categoryNumber)
                .shouldBe(visible,Duration.ofSeconds(10)).is(visible);
    }
}
