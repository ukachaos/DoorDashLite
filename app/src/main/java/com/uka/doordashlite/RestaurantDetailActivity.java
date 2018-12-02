package com.uka.doordashlite;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import Adapters.MRestaurantPresenter;
import Adapters.RestaurantView;
import DataObjects.Restaurant;
import Services.RestaurantDatabase;
import retrofit2.Retrofit;

/**
 * @author uka
 */
public class RestaurantDetailActivity extends AppCompatActivity implements RestaurantView {

    @Inject
    Retrofit retrofit;

    @Inject
    RestaurantDatabase mRestaurantDatabase;

    MRestaurantPresenter mPresenter;

    ImageView mImageCover;

    TextView mTextName;
    TextView mTextDesc;
    TextView mTextStatus;
    TextView mTextFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        ((DAppClass) getApplication()).getComponent().inject(this);

        mImageCover = findViewById(R.id.mImageCover);

        mTextName = findViewById(R.id.mTextName);
        mTextDesc = findViewById(R.id.mTextDesc);
        mTextStatus = findViewById(R.id.mTextStatus);
        mTextFee = findViewById(R.id.mTextFee);

        String restaurant_id = getIntent().getExtras().getString(MainActivity.TAG_RESTAURANT_ID);

        mPresenter = new MRestaurantPresenter(restaurant_id, mRestaurantDatabase, retrofit);
        mPresenter.setView(this);
        mPresenter.start();
    }

    @Override
    public void updateRestaurant(@NonNull Restaurant r) {

        getSupportActionBar().setTitle(r.getName());

        Picasso.get().load(r.getCover_img_url()).fit().into(mImageCover);

        mTextName.setText(r.getName());
        mTextDesc.setText(r.getDescription());
        mTextStatus.setText(r.getStatus());
        mTextFee.setText(r.getDelivery_fee());
    }

    @Override
    public void onError(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
