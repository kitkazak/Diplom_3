package praktikum.testing.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.testing.api.Auth;
import praktikum.testing.pom.ConstructorPOM;
import praktikum.testing.pom.LoginPOM;

import java.util.HashMap;
import java.util.UUID;

public class ConstructorTests {
    private WebDriver driver;
    UUID uuid;
    Response createResponse;


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        uuid = UUID.randomUUID();

        // Создать пользователя по API
        HashMap<String, Object> authRegisterBody = new HashMap<>();
        authRegisterBody.put("email", "kitkazak_email" + uuid.toString() + "@yandex.ru");
        authRegisterBody.put("password", "kitkazak_password" + uuid.toString());
        authRegisterBody.put("name", "kitkazak_name" + uuid.toString());

        createResponse = Auth.register(new JSONObject(authRegisterBody));
        createResponse.then().statusCode(200);
    }

    @After
    public void tearDown() {
        // Удалить пользователя
        Response delRes = Auth.delete(createResponse.jsonPath().get("accessToken"));
        delRes.then().statusCode(202);
        driver.quit();
    }

    @Test
    @DisplayName("Переход по кнопке 'Булки'")
    public void testBunsButton() {
        driver.get("https://stellarburgers.nomoreparties.site/login");

        // Вход
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.setEmailInput("kitkazak_email" + uuid.toString() + "@yandex.ru");
        loginPOM.setPasswordInput("kitkazak_password" + uuid.toString());
        loginPOM.clickLoginButton();

        // Проверяем, что попали на конструктор
        ConstructorPOM constructorPOM = new ConstructorPOM(driver);
        constructorPOM.checkUrl();

        // Переход по кнопке 'Булки'
        // Сначала переходим по кнопке 'Соусы', чтобы кнопка 'Булки' стала кликабельной
        constructorPOM.clickSauceButton();
        constructorPOM.clickBunsButton();
        constructorPOM.checkBunsButtonIsClicked();
    }

    @Test
    @DisplayName("Переход по кнопке 'Соусы'")
    public void testSauceButton() {
        driver.get("https://stellarburgers.nomoreparties.site/login");

        // Вход
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.setEmailInput("kitkazak_email" + uuid.toString() + "@yandex.ru");
        loginPOM.setPasswordInput("kitkazak_password" + uuid.toString());
        loginPOM.clickLoginButton();

        // Проверяем, что попали на конструктор
        ConstructorPOM constructorPOM = new ConstructorPOM(driver);
        constructorPOM.checkUrl();

        // Переход по кнопке 'Соусы'
        constructorPOM.clickSauceButton();
        constructorPOM.checkSauceButtonIsClicked();
    }

    @Test
    @DisplayName("Переход по кнопке 'Начинки'")
    public void testFillingButton() {
        driver.get("https://stellarburgers.nomoreparties.site/login");

        // Вход
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.setEmailInput("kitkazak_email" + uuid.toString() + "@yandex.ru");
        loginPOM.setPasswordInput("kitkazak_password" + uuid.toString());
        loginPOM.clickLoginButton();

        // Проверяем, что попали на конструктор
        ConstructorPOM constructorPOM = new ConstructorPOM(driver);
        constructorPOM.checkUrl();

        constructorPOM.clickFillingButton();
        constructorPOM.checkFillingButtonIsClicked();
    }

    @Before
    public void setupTest() {
        // Без этой строчки ничего не работает...
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        // Chrome
        driver = new ChromeDriver();

        // FF
        // driver = new FirefoxDriver();
    }

}
