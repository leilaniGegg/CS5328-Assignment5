package problem7;

/**
 * Mediator interface that defines the communication methods between riders.
 */
public interface Mediator {
    /**
     * Sends a message to a specific recipient
     * @param message the message content
     * @param recipient the recipient's name
     */
    void send(String message, String recipient);

    /**
     * Broadcasts a message to all riders
     * @param message the message to broadcast
     */
    void broadcast(String message);
}