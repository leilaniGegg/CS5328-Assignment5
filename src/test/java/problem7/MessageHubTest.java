package problem7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageHubTest {

    private MessageHub hub;

    @BeforeEach
    public void setUp() {
        hub = new MessageHub();
    }

    @Test
    public void testAddRider() {
        assertEquals(0, hub.getRiderCount());

        // Create rider with no announcement to avoid broadcast messages in test
        RiderClass rider = new RiderClass("TestRider", hub, false);
        assertEquals(1, hub.getRiderCount());
    }

    @Test
    public void testRemoveRider() {
        // Create rider with no announcement to avoid broadcast messages in test
        RiderClass rider = new RiderClass("TestRider", hub, false);
        assertEquals(1, hub.getRiderCount());

        hub.removeRider(rider);
        assertEquals(0, hub.getRiderCount());
    }

    @Test
    public void testBroadcast() {
        // Create riders with no announcement
        RiderClass rider1 = new RiderClass("Rider1", hub, false);
        RiderClass rider2 = new RiderClass("Rider2", hub, false);

        // Clear any existing messages
        rider1.clearMessages();
        rider2.clearMessages();

        hub.broadcast("Test broadcast message");

        assertEquals(1, rider1.getReceivedMessages().size());
        assertEquals(1, rider2.getReceivedMessages().size());
        assertEquals("Broadcast: Test broadcast message", rider1.getReceivedMessages().get(0));
    }

    @Test
    public void testDirectMessage() {
        // Create riders with no announcement
        RiderClass rider1 = new RiderClass("Rider1", hub, false);
        RiderClass rider2 = new RiderClass("Rider2", hub, false);

        // Clear any existing messages
        rider1.clearMessages();
        rider2.clearMessages();

        hub.send("Test direct message", "Rider1");

        assertEquals(1, rider1.getReceivedMessages().size());
        assertEquals(0, rider2.getReceivedMessages().size());
        assertEquals("Direct Message: Test direct message", rider1.getReceivedMessages().get(0));
    }
}