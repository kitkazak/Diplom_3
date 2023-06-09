package praktikum.testing.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPOM {
    WebDriver driver;

    public ConstructorPOM(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By mainHeader = By.xpath(".//h1[text()='Соберите бургер']");
    By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    By accountButton = By.xpath(".//a[@href=\"/account\"]");
    By sauceButton = By.xpath(".//span[text()='Соусы']/parent::div");
    By fillingButton = By.xpath(".//span[text()='Начинки']/parent::div");
    By bunsButton = By.xpath(".//span[text()='Булки']/parent::div");
    By sauceHeader = By.xpath(".//h2[text()='Соусы']");
    By fillingsHeader = By.xpath(".//h2[text()='Начинки']");
    By bunsHeader = By.xpath(".//h2[text()='Булки']");
    
    public void checkMainHeaderIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(mainHeader)));
        Assert.assertTrue(driver.findElement(mainHeader).isDisplayed());
    }

    public void checkUrl() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", driver.getCurrentUrl());
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }
    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    public void checkFillingHeaderIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(fillingsHeader)));
        Assert.assertTrue(driver.findElement(fillingsHeader).isDisplayed());
    }

    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    public void checkSauceHeaderIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(sauceHeader)));
        Assert.assertTrue(driver.findElement(sauceHeader).isDisplayed());
    }

    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    public void checkBunsHeaderIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(bunsHeader)));
        Assert.assertTrue(driver.findElement(bunsHeader).isDisplayed());
    }
}
