package pages;

import models.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    private static final String URL = "https://www.saucedemo.com";

    private final By username = By.cssSelector("[data-test='username']");
    private final By password = By.cssSelector("[data-test='password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public void loginAs(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
    }

    public void loginAs(Credentials credentials) {
        loginAs(credentials.username, credentials.password);
    }
}