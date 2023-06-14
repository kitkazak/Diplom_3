package praktikum.testing.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPassPOM {
    WebDriver driver;
    public ForgotPassPOM(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By loginButton = By.xpath(".//a[@href=\"/login\"]");

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
