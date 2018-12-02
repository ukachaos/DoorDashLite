package com.uka.doordashlite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import Adapters.BasePresenter;
import Adapters.BaseView;
import Adapters.MMainPresenter;
import Adapters.RestaurantListAdapter;
import DataObjects.Restaurant;
import Services.RestaurantDatabase;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

/**
 * @author uka
 */
public class MainActivity extends AppCompatActivity implements BaseView {

    public static final String TAG_RESTAURANT_ID = "restaurant_id";

    @Inject
    Retrofit retrofit;

    @Inject
    RestaurantDatabase mRestaurantDatabase;

    RecyclerView mMainList;

    RestaurantListAdapter listAdapter;

    RecyclerView.LayoutManager mLayoutManager;

    //Presenter
    public BasePresenter presenter;

    //for on click listeners
    Disposable subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.main_title);

        ((DAppClass) getApplication()).getComponent().inject(this);

        mMainList = findViewById(R.id.mMainList);

        mLayoutManager = new LinearLayoutManager(this);
        mMainList.setLayoutManager(mLayoutManager);

        List<Restaurant> tmpList = new ArrayList<>();

        Restaurant r = new Restaurant();
        r.setName("Loading data...");
        r.setDescription("Please wait");

        tmpList.add(r);

        listAdapter = new RestaurantListAdapter(tmpList);

        mMainList.setAdapter(listAdapter);

        subscribe = listAdapter.getClickEvent().subscribe(id -> {
            onListItemClicked(id);
        });

        presenter = new MMainPresenter(this, retrofit, mRestaurantDatabase);
        presenter.start();
    }

    //Start
    public void onListItemClicked(@NonNull String id) {
            Intent intent = new Intent(MainActivity.this, RestaurantDetailActivity.class);
            intent.putExtra(MainActivity.TAG_RESTAURANT_ID, id);
            startActivity(intent);
    }

    @Override
    public void showTasks(@NonNull List<Restaurant> list) {
        listAdapter.replaceDatabaset(list);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
