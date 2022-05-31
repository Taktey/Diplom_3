package tests;

import TestTools.TestData;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerConstructorTest {
    TestData testData;
    MainPage mainPage;
    int ingredientNumber;

    public BurgerConstructorTest(int ingredientNumber) {
        this.ingredientNumber = ingredientNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {0},
                {1},
                {2},
        };
    }

    @Before
    public void initializationTestData() {
        System.setProperty("selenide.browser", "Chrome");
        testData = new TestData();
        mainPage = page(MainPage.class);
        open(testData.getMainPageURL(), MainPage.class);
    }

    @Test
    @DisplayName("Тест вкладом конструктора бургера")
    @Description("Нажатие клавиш-вкладок в списке ингредиентов приводит к переходу в соответствующий раздел списка ингредиентов")
    public void isBurgerConstructorTubsLeadToIngredientSectionsTest() {
        if (ingredientNumber == 0) {
            mainPage.doIngredientTabClick(1);
            mainPage.isIngredientCategoriesHeaderVisible(1);
        }
        mainPage.doIngredientTabClick(ingredientNumber);
        boolean expected = true;
        boolean actual = mainPage.isIngredientCategoriesHeaderVisible(ingredientNumber);
        assertEquals(expected, actual);
    }
}
