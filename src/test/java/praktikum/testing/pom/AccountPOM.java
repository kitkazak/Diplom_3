package praktikum.testing.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPOM {
    WebDriver driver;
    public AccountPOM(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By constructorButtons = By.xpath(".//a[@href='/']");
    By exitButton = By.xpath(".//button[text()='Выход']");

    public void checkUrl() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/account/profile", driver.getCurrentUrl());
    }

    public void clickConstuctorButton() {
        driver.findElements(constructorButtons).get(0).click();
    }

    public void clickLogo() {
        driver.findElements(constructorButtons).get(1).click();
    }

    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
}
