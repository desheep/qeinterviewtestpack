package stepdefs;

import context.ScenarioContext;
import io.cucumber.java.en.*;
import models.Credentials;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefs {

    private final ScenarioContext scenarioContext;

    public StepDefs(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I add the {string} to my cart")
    public void addToCart(String productName) {
        scenarioContext.getInventoryPage().addToCart(productName);
    }

    @Then("My cart shows {string} items added")
    public void itemsCountInCart(String expectedCount) {
        boolean itemsWereAddedToTheCart =
                scenarioContext.getInventoryPage().wereItemsAddedToTheCart(expectedCount);

        assertTrue(
                itemsWereAddedToTheCart,
                "Fail: Expected number of " + expectedCount + " items were not in the cart"
        );
    }
}