package context;

import models.Users;
import models.User;
import org.openqa.selenium.WebDriver;
import pages.InventoryPage;
import pages.LandingPage;

public class ScenarioContext {

    private WebDriver driver;
    private LandingPage landingPage;
    private InventoryPage inventoryPage;
    private Users users;
    private User currentUser;

    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }
    public Users getUsers() { return users; }
    public void setUsers(Users users) { this.users = users; }

    public WebDriver getDriver() { return driver; }
    public void setDriver(WebDriver driver) { this.driver = driver; }

    public LandingPage getLandingPage() { return landingPage; }
    public void setLandingPage(LandingPage landingPage) { this.landingPage = landingPage; }

    public InventoryPage getInventoryPage() { return inventoryPage; }
    public void setInventoryPage(InventoryPage inventoryPage) { this.inventoryPage = inventoryPage; }
}