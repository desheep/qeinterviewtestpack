package hooks;

import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

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

                options.addArguments("--no-first-run");
                options.addArguments("--no-default-browser-check");
                options.addArguments("--disable-notifications");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                options.setExperimentalOption("prefs", prefs);

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