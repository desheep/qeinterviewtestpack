package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;

    // Hardcoded, sensible defaults (Playwright-ish feel)
    private static final int TIMEOUT_SECONDS = 5;
    private static final int POLL_MILLIS = 200;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected FluentWait<WebDriver> waitDriver() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofMillis(POLL_MILLIS))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    protected WebElement waitForVisible(By locator) {
        return waitDriver().until(d -> {
            WebElement el = d.findElement(locator);
            return el.isDisplayed() ? el : null;
        });
    }

    protected WebElement waitForClickable(By locator) {
        return waitDriver().until(d -> {
            WebElement el = d.findElement(locator);
            if (!el.isDisplayed() || !el.isEnabled()) return null;
            return el;
        });
    }

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement el = waitForVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String text(By locator) {
        return waitForVisible(locator).getText();
    }

    protected boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected boolean isVisible(By locator) {
        try {
            return waitForVisible(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}