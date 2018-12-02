package Adapters;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import DataObjects.Restaurant;
import Services.RestaurantDatabase;
import Services.RestaurantService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MRestaurantPresenter {
    String id;

    RestaurantDatabase db;

    RestaurantView mView;

    Retrofit retrofit;

    public MRestaurantPresenter(@NonNull String id, @NonNull RestaurantDatabase db, @NonNull Retrofit retrofit) {
        this.id = id;
        setDB(db);
        setRetrofit(retrofit);
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void setDB(@NonNull RestaurantDatabase db) {
        this.db = db;
    }

    public void setView(RestaurantView mView) {
        this.mView = mView;
    }

    public void start() {
        new GetRestaurantTask().execute(id);
    }

    void processRestaurant(Restaurant r) {
        mView.updateRestaurant(r);
    }

    /**
     * called if something went wrong with db call or the data don't exist in database then get it from endpoint
     */
    void onDatabaseError() {
        getRestaurant(this.id);
    }

    void getRestaurant(String id) {
        RestaurantService service = retrofit.create(RestaurantService.class);

        Call<Restaurant> restaurantCall = service.getRestaurantByID(id);

        restaurantCall.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                Restaurant r = response.body();

                if (r != null) {
                    mView.updateRestaurant(r);
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                mView.onError("Something went wrong, try again later!");
            }
        });
    }

    class GetRestaurantTask extends AsyncTask<String, Void, Restaurant> {

        @Override
        protected Restaurant doInBackground(String... strings) {
            try {
                Restaurant r = db.doaAccess().getRestaurantByID(strings[0]);
                return r;
            } catch (Exception e) {
                e.printStackTrace();
            }

            onDatabaseError();
            return null;
        }

        @Override
        protected void onPostExecute(Restaurant restaurant) {
            if (restaurant != null) {
                processRestaurant(restaurant);
            }
        }
    }
}
