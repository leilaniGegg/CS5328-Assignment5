package problem11;

import org.junit.jupiter.api.Test;
import problem11.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User("u1", "John", "New York");

        assertEquals("u1", user.getId());
        assertEquals("John", user.getName());
        assertEquals("New York", user.getCity());
    }

    @Test
    public void testUserToString() {
        User user = new User("u2", "Alice", "San Francisco");
        String userString = user.toString();

        assertTrue(userString.contains("u2"));
        assertTrue(userString.contains("Alice"));
        assertTrue(userString.contains("San Francisco"));
    }
}