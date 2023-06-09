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
import praktikum.testing.pom.RegisterPOM;

import java.time.Duration;
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
