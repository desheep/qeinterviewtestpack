package hooks;

import context.ScenarioContext;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverHooks {

    private final ScenarioContext scenarioContext;

    public DriverHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before(order = 0)
    public void startDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        WebDriver driver;

        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless");
                }
                driver = new ChromeDriver(options);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser (not implemented yet): " + browser);
        }

        scenarioContext.setDriver(driver);
    }

    @After(order = 0)
    public void stopDriver() {
        WebDriver driver = scenarioContext.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}