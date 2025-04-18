package problem11.interfaces;

import problem11.model.User;

public interface FoodDeliveryServiceInterface {
    int orderFood(User user, String restaurant, String items);
    boolean cancelFoodOrder(int orderId);
    boolean trackFoodOrder(int orderId);
}
