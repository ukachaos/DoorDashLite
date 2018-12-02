package DataObjects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RestaurantDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Restaurant> list);

    @Query("SELECT * FROM Restaurant WHERE id=:id")
    Restaurant getRestaurantByID(String id);
}
