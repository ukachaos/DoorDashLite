package Adapters;

import DataObjects.Restaurant;

/**
 * @author uka
 */
public interface RestaurantView {
    void updateRestaurant(Restaurant r);
    void onError(String message);
}
