package problem7;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rider in the social network system.
 * Implements the RiderObserver interface to receive updates.
 */
public class RiderClass implements RiderObserver {
    private final String name;
    private final Mediator mediator;
    private final List<String> receivedMessages;
    private boolean announceJoin;

    /**
     * Creates a new rider and registers them with the mediator
     * @param name the rider's name
     * @param mediator the messaging mediator
     */
    public RiderClass(String name, Mediator mediator) {
        this(name, mediator, true);
    }

    /**
     * Creates a new rider and registers them with the mediator
     * @param name the rider's name
     * @param mediator the messaging mediator
     * @param announceJoin whether to announce joining
     */
    public RiderClass(String name, Mediator mediator, boolean announceJoin) {
        this.name = name;
        this.mediator = mediator;
        this.receivedMessages = new ArrayList<>();
        this.announceJoin = announceJoin;

        // Add rider to the mediator
        if (mediator instanceof MessageHub) {
            ((MessageHub) mediator).addRider(this);
        }

        // Announce arrival if requested
        if (announceJoin) {
            this.mediator.broadcast(name + " has joined the network.");
        }
    }

    /**
     * Gets the rider's name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Posts a message to all riders
     * @param message the message to post
     */
    public void postMessage(String message) {
        mediator.broadcast(name + ": " + message);
    }

    /**
     * Sends a direct message to a specific recipient
     * @param message the message content
     * @param recipient the recipient's name
     */
    public void sendDirectMessage(String message, String recipient) {
        mediator.send(name + ": " + message, recipient);
    }

    @Override
    public void update(String message) {
        receivedMessages.add(message);
        System.out.println(name + " received -> " + message);
    }

    /**
     * Gets all messages this rider has received
     * @return list of received messages
     */
    public List<String> getReceivedMessages() {
        return new ArrayList<>(receivedMessages);
    }

    /**
     * Clears all received messages
     */
    public void clearMessages() {
        receivedMessages.clear();
    }
}