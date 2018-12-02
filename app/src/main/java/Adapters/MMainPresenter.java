package Adapters;

import android.location.Location;
import android.os.AsyncTask;

import java.util.List;

import DataObjects.Restaurant;
import Services.RestaurantDatabase;
import Services.RestaurantService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author uka
 */
public class MMainPresenter implements BasePresenter {

    BaseView mView;

    Retrofit retrofit;

    RestaurantDatabase db;

    //Offset for REST query
    int offset = 0;

    //Limit for REST query
    final static int limit = 50;

    Location mCurrentLocation;


    public MMainPresenter() {
        mCurrentLocation = new Location("Current location");
        mCurrentLocation.setLatitude(37.422740);
        mCurrentLocation.setLongitude(-122.139956);
    }

    public MMainPresenter(BaseView view, Retrofit retrofit, RestaurantDatabase db) {
        this();
        setView(view);
        setRetrofit(retrofit);
        setDB(db);
    }

    public void setDB(RestaurantDatabase db) {
        this.db = db;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void setView(BaseView view) {
        if (view != null)
            mView = view;
    }

    @Override
    public void start() {
        GetRestaurants(mCurrentLocation);
    }

    void GetRestaurants(Location location) {
        RestaurantService service = retrofit.create(RestaurantService.class);

        Call<List<Restaurant>> restaurants = service.getRestaurants(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()), offset, limit);

        restaurants.enqueue(new Callback<List<Restaurant>>() {

            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                List<Restaurant> res = response.body();

                if (res != null)
                    processResult(res);
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                processError("Something went wrong!!");
            }
        });
    }

    /**
     * Gets result list, save to database in background and pass to view to update UI
     * @param list
     */
    public void processResult(List<Restaurant> list) {
        new SaveRestaurantsTask().execute(list);
        mView.showTasks(list);
    }

    public void processResultOnly(List<Restaurant> list){
        mView.showTasks(list);
    }

    void processError(String message) {
        mView.onError(message);
    }

    /**
     * Task to save all restaurant informations into database
     */
    class SaveRestaurantsTask extends AsyncTask<List<Restaurant>, Void, Void> {

        @Override
        protected Void doInBackground(List<Restaurant>... lists) {
            db.doaAccess().insertAll(lists[0]);
            return null;
        }
    }
}
