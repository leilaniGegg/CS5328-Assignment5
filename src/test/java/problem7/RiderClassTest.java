package problem7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RiderClassTest {

    private MessageHub hub;
    private RiderClass rider1;
    private RiderClass rider2;

    @BeforeEach
    public void setUp() {
        hub = new MessageHub();
        // Create riders with no announcement to avoid broadcast messages
        rider1 = new RiderClass("Rider1", hub, false);
        rider2 = new RiderClass("Rider2", hub, false);

        // Clear any existing messages
        rider1.clearMessages();
        rider2.clearMessages();
    }

    @Test
    public void testRiderCreation() {
        assertEquals("Rider1", rider1.getName());
        assertEquals(2, hub.getRiderCount());
    }

    @Test
    public void testPostMessage() {
        rider1.postMessage("Hello network!");

        assertEquals(1, rider1.getReceivedMessages().size());
        assertEquals(1, rider2.getReceivedMessages().size());

        String expectedMessage = "Broadcast: Rider1: Hello network!";
        assertEquals(expectedMessage, rider1.getReceivedMessages().get(0));
        assertEquals(expectedMessage, rider2.getReceivedMessages().get(0));
    }

    @Test
    public void testDirectMessage() {
        rider1.sendDirectMessage("Private message", "Rider2");

        assertEquals(0, rider1.getReceivedMessages().size());
        assertEquals(1, rider2.getReceivedMessages().size());

        String expectedMessage = "Direct Message: Rider1: Private message";
        assertEquals(expectedMessage, rider2.getReceivedMessages().get(0));
    }

    @Test
    public void testMultipleMessages() {
        rider1.postMessage("First message");
        rider2.postMessage("Second message");
        rider1.sendDirectMessage("Third message", "Rider2");

        assertEquals(2, rider1.getReceivedMessages().size());
        assertEquals(3, rider2.getReceivedMessages().size());
    }
}