package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefs {

    WebDriver driver = new ChromeDriver();

    @Given("I log into the website as a regular user")
    public void regularUserLogin() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("I add the {string} to my cart")
    public void addToCart(String productName) {
        String nameToUse = productName.replace(" ", "-");
        driver.findElement(By.id(String.format("add-to-cart-sauce-labs-%s", nameToUse))).click();
    }

    @Then("My cart shows {string} items added")
    public void itemsCountInCart(String numberOfItems) {
        String expected = driver.findElement(By.cssSelector("span.shopping_cart_badge")).getText();
        assertEquals(expected, numberOfItems);
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}
