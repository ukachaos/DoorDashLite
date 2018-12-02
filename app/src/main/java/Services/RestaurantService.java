package Services;

import java.util.List;

import DataObjects.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author uka
 */
public interface RestaurantService {

    /**
     * Gets all restaurants near the (lat, lon) position from endpoint
     *
     * @param lat    location latitude
     * @param lng    location longitude
     * @param offset offset
     * @return List of Calls of Restaurants
     */
    @GET("/v2/restaurant/")
    Call<List<Restaurant>> getRestaurants(@Query("lat") String lat, @Query("lng") String lng, @Query("offset") int offset, @Query("limit") int limit);

    /**
     * Get one restaurant by its id from endpoint
     * @param resId
     * @return
     */
    @GET("/v2/restaurant/{resid}")
    Call<Restaurant> getRestaurantByID(@Path("resid") String resId);
}
