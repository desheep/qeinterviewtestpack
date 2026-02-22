package stepdefs;

import context.ScenarioContext;
import io.cucumber.java.en.Given;
import models.Credentials;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationSteps {

    private final ScenarioContext scenarioContext;

    public AuthenticationSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I log into the website as a regular user")
    public void regularUserLogin() {
        scenarioContext.getLandingPage().open();

        Credentials credentials = scenarioContext.getCurrentUser().credentials;
        scenarioContext.getLandingPage().loginAs(credentials);

        assertTrue(
                scenarioContext.getInventoryPage().isLoaded(),
                "Inventory page did not load after login"
        );
    }
}