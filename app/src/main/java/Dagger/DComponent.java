package Dagger;

import com.uka.doordashlite.MainActivity;
import com.uka.doordashlite.RestaurantDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author uka
 */
@Singleton
@Component(modules = DModule.class)
public interface DComponent {
    void inject(MainActivity activity);
    void inject(RestaurantDetailActivity activity);
}
