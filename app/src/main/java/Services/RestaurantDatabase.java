package Services;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import DataObjects.Restaurant;
import DataObjects.RestaurantDAO;

@Database(entities = {Restaurant.class}, version = 1)
public abstract class RestaurantDatabase extends RoomDatabase {
    public abstract RestaurantDAO doaAccess();

    private static RestaurantDatabase INSTANCE;

    public synchronized static RestaurantDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), RestaurantDatabase.class).build();
        }

        return INSTANCE;
    }
}
