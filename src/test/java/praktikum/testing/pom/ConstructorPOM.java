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
    
    public void checkMainHeaderIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(mainHeader)));
        Assert.assertTrue(driver.findElement(mainHeader).isDisplayed());
    }
}
