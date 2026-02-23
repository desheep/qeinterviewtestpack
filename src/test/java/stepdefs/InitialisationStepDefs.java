package stepdefs;

import context.ScenarioContext;
import data.UserDataLoader;
import io.cucumber.java.en.Given;
import models.User;
import models.Users;

public class InitialisationStepDefs {

    private final ScenarioContext scenarioContext;

    public InitialisationStepDefs(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I have test data for the {string} user")
    public void iHaveTestDataForTheUser(String userKey) {
        Users users = UserDataLoader.loadUsersOnce();

        User selectedUser;

        switch (userKey) {
            case "regular":
                selectedUser = users.regularUser;
                break;
            case "locked_out":
                selectedUser = users.lockedOutUser;
                break;
            default:
                throw new IllegalArgumentException("Unknown user key: " + userKey);
        }

        scenarioContext.setCurrentUser(selectedUser);
    }
}