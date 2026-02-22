package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Users;

import java.io.InputStream;

public class UserDataLoader {

    private static Users cachedUsers;

    private UserDataLoader() { }

    public static Users loadUsersOnce() {
        if (cachedUsers != null) return cachedUsers;

        try (InputStream is = UserDataLoader.class.getClassLoader()
                .getResourceAsStream("data/json/users.json")) {

            if (is == null) {
                throw new IllegalStateException("Could not find resource: data/json/users.json");
            }

            cachedUsers = new ObjectMapper().readValue(is, Users.class);
            return cachedUsers;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load users.json", e);
        }
    }
}