package hooks;

import context.ScenarioContext;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.InventoryPage;
import pages.LandingPage;

public class PageHooks {

    private final ScenarioContext scenarioContext;

    public PageHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before(order = 10)
    public void createPages() {
        WebDriver driver = scenarioContext.getDriver();
        scenarioContext.setLandingPage(new LandingPage(driver));
        scenarioContext.setInventoryPage(new InventoryPage(driver));
    }
}