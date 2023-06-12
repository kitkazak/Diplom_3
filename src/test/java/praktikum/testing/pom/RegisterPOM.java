package praktikum.testing.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegisterPOM {
    WebDriver driver;
    public RegisterPOM(WebDriver driver) {this.driver = driver;}

    // Locators
    By nameInput = By.xpath(".//label[text()='Имя']/parent::div/input");
    By emailInput = By.xpath(".//label[text()='Email']/parent::div/input");
    By passwordInput = By.xpath(".//label[text()='Пароль']/parent::div/input");
    By regButton = By.xpath(".//button[text()='Зарегистрироваться']");
    By invalidPasswordLabel = By.xpath(".//p[text()='Некорректный пароль']");
    By loginButton = By.xpath(".//a[@href=\"/login\"]");

    public void setNameInput(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmailInput(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickRegButton() {
        driver.findElement(regButton).click();
    }

    public void checkInvalidPasswordLabelIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(invalidPasswordLabel)));
        assertTrue(driver.findElement(invalidPasswordLabel).isDisplayed());
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
