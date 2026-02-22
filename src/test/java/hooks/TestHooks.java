package hooks;

import context.ScenarioContext;
import data.UserDataLoader;
import io.cucumber.java.Before;
import models.Users;

public class TestHooks {

    private final ScenarioContext scenarioContext;

    public TestHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before(order =5)
    public void beforeScenario() {
        Users users = UserDataLoader.loadUsersOnce();
        scenarioContext.setUsers(users);
    }
}