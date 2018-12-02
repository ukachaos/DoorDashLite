package Adapters;

import java.util.List;

import DataObjects.Restaurant;

/**
 * @author uka
 */
public interface BaseView {
    void showTasks(List<Restaurant> list);
    void onError(String message);
}
