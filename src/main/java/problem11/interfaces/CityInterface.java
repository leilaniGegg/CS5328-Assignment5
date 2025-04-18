package problem11.interfaces;

import problem11.model.User;

public interface CityInterface {
    String getName();
    boolean isServiceAvailable(String service);
    void broadcast(String message);
    void sendDirectMessage(User user, String message);
}
