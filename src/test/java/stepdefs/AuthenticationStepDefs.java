package stepdefs;

import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Credentials;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationStepDefs {

    private final ScenarioContext scenarioContext;

    public AuthenticationStepDefs(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I log into the website as a regular user")
    public void regularUserLogin() {
        scenarioContext.getLandingPage().open();

        Credentials credentials = scenarioContext.getCurrentUser().credentials;
        scenarioContext.getLandingPage().loginAs(credentials);

        assertTrue(
                scenarioContext.getInventoryPage().isLoaded(),
                "Fail: Inventory page did not load after login"
        );
    }

    @When("I try to log into the website as the locked out user")
    public void iTryToLogIntoTheWebsiteAsTheLockedOutUser() {
        scenarioContext.getLandingPage().open();

        Credentials credentials = scenarioContext.getCurrentUser().credentials;
        scenarioContext.getLandingPage().loginAs(credentials);
    }

    @Then("I see an error message telling me I'm locked out")
    public void iSeeAnErrorMessageTellingMeImLockedOut() {
        assertTrue(
                scenarioContext.getLandingPage().isErrorDisplayed(),
                "Fail: Expected an error banner to be displayed, but it was not"
        );

        String message = scenarioContext.getLandingPage().getErrorMessage();

        assertTrue(
                message.contains("locked out"),
                "Fail: Expected locked out error message, but got: " + message
        );
    }
}