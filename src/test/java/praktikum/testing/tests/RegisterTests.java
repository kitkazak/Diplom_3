package praktikum.testing.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.C;
import org.json.simple.JSONObject;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.testing.api.Auth;
import praktikum.testing.pom.LoginPOM;
import praktikum.testing.pom.RegisterPOM;

import java.time.Duration;
import java.util.HashMap;
import java.util.UUID;

public class RegisterTests {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() throws InterruptedException {
        UUID uuid = UUID.randomUUID();

        RegisterPOM registerPOM = new RegisterPOM(driver);
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registerPOM.setNameInput("Nikita");
        registerPOM.setPasswordInput("passw0rd");
        registerPOM.setEmailInput(uuid.toString() + "kitkazak@gmail.com");
        registerPOM.clickRegButton();

        // Url поменялся после регистрации
        LoginPOM loginPOM = new LoginPOM(driver);
        loginPOM.checkUrl();

        // Login to get access token
        HashMap<String, Object> loginBody = new HashMap<>();
        loginBody.put("email", uuid.toString() + "kitkazak@gmail.com");
        loginBody.put("password", "passw0rd");
        Response loginRes = Auth.login(new JSONObject(loginBody));
        loginRes.then().statusCode(200);

        // Delete user
        Response deleteRes = Auth.delete(loginRes.jsonPath().get("accessToken"));
        deleteRes.then().statusCode(202);
    }

    @Test
    @DisplayName("Ошибку для некорректного пароля")
    @Description("Минимальный пароль — шесть символов")
    public void registrationWithInvalidPassword() {
        UUID uuid = UUID.randomUUID();

        RegisterPOM registerPOM = new RegisterPOM(driver);
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registerPOM.setNameInput("Nikita");
        registerPOM.setPasswordInput("123");
        registerPOM.setEmailInput(uuid.toString() + "kitkazak@gmail.com");
        registerPOM.clickRegButton();

        registerPOM.checkInvalidPasswordLabelIsVisible();
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
