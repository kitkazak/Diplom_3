package praktikum.testing.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.testing.api.Auth;
import praktikum.testing.pom.AccountPOM;
import praktikum.testing.pom.ConstructorPOM;
import praktikum.testing.pom.LoginPOM;

import java.util.HashMap;
import java.util.UUID;

public class AccountTests {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void clickOnAccountButton() {
        UUID uuid = UUID.randomUUID();

        // Создать пользователя по API
        HashMap<String, Object> authRegisterBody = new HashMap<>();
        authRegisterBody.put("email", "kitkazak_email" + uuid.toString() + "@yandex.ru");
        authRegisterBody.put("password", "kitkazak_password" + uuid.toString());
        authRegisterBody.put("name", "kitkazak_name" + uuid.toString());

        Response res = Auth.register(new JSONObject(authRegisterBody));
        res.then().statusCode(200);

        driver.get("https://stellarburgers.nomoreparties.site/login");

        // Вход
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.setEmailInput("kitkazak_email" + uuid.toString() + "@yandex.ru");
        loginPOM.setPasswordInput("kitkazak_password" + uuid.toString());
        loginPOM.clickLoginButton();

        // Проверяем, что попали на конструктор
        ConstructorPOM constructorPOM = new ConstructorPOM(driver);
        constructorPOM.checkUrl();

        // Проверяем переход в личный кабинет
        constructorPOM.clickAccountButton();
        AccountPOM accountPOM = new AccountPOM(driver);
        accountPOM.checkUrl();

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
