package problem7;

/**
 * Observer interface for riders to receive updates.
 */
public interface RiderObserver {
    /**
     * Update method called when a message is received
     * @param message the received message
     */
    void update(String message);
}