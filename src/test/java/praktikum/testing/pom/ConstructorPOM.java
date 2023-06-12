package praktikum.testing.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void checkFillingButtonIsClicked() {
        Assert.assertTrue(driver.findElement(fillingButton).getAttribute("class").contains("tab_tab_type_current__2BEPc"));
    }

    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    public void checkSauceButtonIsClicked() {
        Assert.assertTrue(driver.findElement(sauceButton).getAttribute("class").contains("tab_tab_type_current__2BEPc"));
    }

    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    public void checkBunsButtonIsClicked() {
        Assert.assertTrue(driver.findElement(bunsButton).getAttribute("class").contains("tab_tab_type_current__2BEPc"));
    }
}
