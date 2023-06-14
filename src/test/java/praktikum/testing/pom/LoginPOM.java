package praktikum.testing.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPOM {
    WebDriver driver;

    public LoginPOM(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By emailInput = By.xpath(".//label[text()='Email']/parent::div/input");
    By passwordInput = By.xpath(".//label[text()='Пароль']/parent::div/input");
    By loginButton = By.xpath(".//button[text()='Войти']");

    public void setEmailInput(String name) {
        driver.findElement(emailInput).sendKeys(name);
    }

    public void setPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void checkUrl() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");
    }

}
