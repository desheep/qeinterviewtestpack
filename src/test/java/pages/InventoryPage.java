package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By inventoryContainer = By.id("inventory_container");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isPresent(inventoryContainer);
    }

    public void addToCart(String productName) {
        String nameToUse = productName.replace(" ", "-");
        By addButton = By.cssSelector(String.format("[data-test='add-to-cart-sauce-labs-%s']", nameToUse));
        click(addButton);
    }

    public boolean wereItemsAddedToTheCart(String expectedCount) {
        String actualCount = getCartBadgeCount();
        return expectedCount.equals(actualCount);
    }

    public String getCartBadgeCount() {
        return text(cartBadge);
    }
}