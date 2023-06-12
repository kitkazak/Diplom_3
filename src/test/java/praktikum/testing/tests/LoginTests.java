package praktikum.testing.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.testing.api.Auth;
import praktikum.testing.pom.ConstructorPOM;
import praktikum.testing.pom.ForgotPassPOM;
import praktikum.testing.pom.LoginPOM;
import praktikum.testing.pom.RegisterPOM;

import java.util.HashMap;
import java.util.UUID;

public class LoginTests {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        // Chrome
        WebDriverManager.chromedriver().setup();

        // FF
        // WebDriverManager.firefoxdriver().setup();
    }

    @Test
    @DisplayName("Вход с главной страницы")
    public void loginFromConstructor() {
        UUID uuid = UUID.randomUUID();

        // Создать пользователя по API
        HashMap<String, Object> authRegisterBody = new HashMap<>();
        authRegisterBody.put("email", "kitkazak_email" + uuid.toString() + "@yandex.ru");
        authRegisterBody.put("password", "kitkazak_password" + uuid.toString());
        authRegisterBody.put("name", "kitkazak_name" + uuid.toString());

        Response res = Auth.register(new JSONObject(authRegisterBody));
        res.then().statusCode(200);

        driver.get("https://stellarburgers.nomoreparties.site");

        // Переход на страницу логина
        ConstructorPOM constructorPOM = new ConstructorPOM(driver);
        constructorPOM.clickLoginButton();
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.checkUrl();

        loginPOM.setEmailInput("kitkazak_email" + uuid.toString() + "@yandex.ru");
        loginPOM.setPasswordInput("kitkazak_password" + uuid.toString());
        loginPOM.clickLoginButton();

        // Вернулись на страницу с конструктором
        constructorPOM.checkUrl();
        constructorPOM.checkMainHeaderIsVisible();

        // Удалить пользователя
        Response delRes = Auth.delete(res.jsonPath().get("accessToken"));
        delRes.then().statusCode(202);
    }

    @Test
    @DisplayName("Вход через личный кабинет")
    public void loginFromAccountButton() {
        UUID uuid = UUID.randomUUID();

        // Создать пользователя по API
        HashMap<String, Object> authRegisterBody = new HashMap<>();
        authRegisterBody.put("email", "kitkazak_email" + uuid.toString() + "@yandex.ru");
        authRegisterBody.put("password", "kitkazak_password" + uuid.toString());
        authRegisterBody.put("name", "kitkazak_name" + uuid.toString());

        Response res = Auth.register(new JSONObject(authRegisterBody));
        res.then().statusCode(200);

        driver.get("https://stellarburgers.nomoreparties.site");

        // Переход на страницу логина
        ConstructorPOM constructorPOM = new ConstructorPOM(driver);
        constructorPOM. clickAccountButton();
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.checkUrl();

        loginPOM.setEmailInput("kitkazak_email" + uuid.toString() + "@yandex.ru");
        loginPOM.setPasswordInput("kitkazak_password" + uuid.toString());
        loginPOM.clickLoginButton();

        // Вернулись на страницу с конструктором
        constructorPOM.checkUrl();
        constructorPOM.checkMainHeaderIsVisible();

        // Удалить пользователя
        Response delRes = Auth.delete(res.jsonPath().get("accessToken"));
        delRes.then().statusCode(202);
    }

    @Test
    @DisplayName("Вход через страницу регистрации")
    public void loginFromRegisterPage() {
        UUID uuid = UUID.randomUUID();

        // Создать пользователя по API
        HashMap<String, Object> authRegisterBody = new HashMap<>();
        authRegisterBody.put("email", "kitkazak_email" + uuid.toString() + "@yandex.ru");
        authRegisterBody.put("password", "kitkazak_password" + uuid.toString());
        authRegisterBody.put("name", "kitkazak_name" + uuid.toString());

        Response res = Auth.register(new JSONObject(authRegisterBody));
        res.then().statusCode(200);

        driver.get("https://stellarburgers.nomoreparties.site/register");

        // Переход на страницу логина
        RegisterPOM registerPOM = new RegisterPOM(driver);
        registerPOM.clickLoginButton();
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.checkUrl();

        loginPOM.setEmailInput("kitkazak_email" + uuid.toString() + "@yandex.ru");
        loginPOM.setPasswordInput("kitkazak_password" + uuid.toString());
        loginPOM.clickLoginButton();

        // Вернулись на страницу с конструктором
        ConstructorPOM constructorPOM = new ConstructorPOM(driver);
        constructorPOM.checkUrl();
        constructorPOM.checkMainHeaderIsVisible();

        // Удалить пользователя
        Response delRes = Auth.delete(res.jsonPath().get("accessToken"));
        delRes.then().statusCode(202);
    }

    @Test
    @DisplayName("Вход через страницу восстановления пароля")
    public void loginFromForgotPassPage() {
        UUID uuid = UUID.randomUUID();

        // Создать пользователя по API
        HashMap<String, Object> authRegisterBody = new HashMap<>();
        authRegisterBody.put("email", "kitkazak_email" + uuid.toString() + "@yandex.ru");
        authRegisterBody.put("password", "kitkazak_password" + uuid.toString());
        authRegisterBody.put("name", "kitkazak_name" + uuid.toString());

        Response res = Auth.register(new JSONObject(authRegisterBody));
        res.then().statusCode(200);

        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        // Переход на страницу логина
        ForgotPassPOM forgotPassPOM = new ForgotPassPOM(driver);
        forgotPassPOM.clickLoginButton();
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.checkUrl();

        loginPOM.setEmailInput("kitkazak_email" + uuid.toString() + "@yandex.ru");
        loginPOM.setPasswordInput("kitkazak_password" + uuid.toString());
        loginPOM.clickLoginButton();

        // Вернулись на страницу с конструктором
        ConstructorPOM constructorPOM = new ConstructorPOM(driver);
        constructorPOM.checkUrl();
        constructorPOM.checkMainHeaderIsVisible();

        // Удалить пользователя
        Response delRes = Auth.delete(res.jsonPath().get("accessToken"));
        delRes.then().statusCode(202);
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

    @After
    public void tearDown() {
        driver.quit();
    }
}
