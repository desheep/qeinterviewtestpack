package stepdefs;

import context.ScenarioContext;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryStepDefs {

    private final ScenarioContext scenarioContext;

    public InventoryStepDefs(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I add the {string} to my cart")
    public void addToCart(String productName) {
        scenarioContext.getInventoryPage().addToCart(productName);
    }

    @When("I remove the {string} from my cart")
    public void iRemoveTheItemFromMyCart(String productName) {
        scenarioContext.getInventoryPage().removeFromCart(productName);
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

    @Then("I can see the inventory page")
    public void iCanSeeTheInventoryPage() {
        assertTrue(
                scenarioContext.getInventoryPage().isLoaded(),
                "Fail: Inventory page was not visible"
        );
    }

    @Then("my cart is empty")
    public void myCartIsEmpty() {
        assertTrue(
                scenarioContext.getInventoryPage().isCartEmpty(),
                "Fail: Expected cart to be empty, but it was not"
        );
    }
}