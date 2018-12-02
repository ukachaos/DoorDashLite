package Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uka.doordashlite.R;

import java.util.List;

import DataObjects.Restaurant;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * @author Uka
 *
 * RecyclerView adapter
 */
public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RViewHolder> {

    private final PublishSubject<String> onClickSubject = PublishSubject.create();

    public Observable<String> getClickEvent() {
        return clickEvent;
    }

    Observable<String> clickEvent = onClickSubject;

    //List containing all restaurant inforamtions
    List<Restaurant> mDataset;

    /**
     * Main constructor
     * @param mDataset initial data set
     */
    public RestaurantListAdapter(List<Restaurant> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_view, viewGroup, false);

        RViewHolder viewHolder = new RViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder rViewHolder, int i) {
        Restaurant r = mDataset.get(i);

        rViewHolder.mTextName.setText(r.getName());
        rViewHolder.mTextDesc.setText(r.getDescription());
        rViewHolder.mTextStatus.setText(r.getStatus());
        Picasso.get().load(r.getCover_img_url()).fit().into(rViewHolder.mImageCover);

        rViewHolder.layout.setTag(r.getId());
        rViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(v.getTag().toString());
            }
        });
    }

    public void replaceDatabaset(List<Restaurant> dataset){
        if(dataset!=null){
            mDataset = dataset;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * Empties the adapter
     */
    public void clearList() {
        mDataset.clear();
    }

    /**
     * Add new item to list
     *
     * @param r
     */
    public void addItem(Restaurant r) {
        mDataset.add(r);
    }

    //View holder class
    public static class RViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextName;
        public TextView mTextDesc;
        public TextView mTextStatus;

        public ImageView mImageCover;

        public View layout;

        public RViewHolder(View v) {
            super(v);

            this.mTextName = v.findViewById(R.id.mTextName);
            this.mTextDesc = v.findViewById(R.id.mTextDesc);
            this.mTextStatus = v.findViewById(R.id.mTextStatus);

            this.mImageCover = v.findViewById(R.id.mImageCover);

            this.layout = v;
        }
    }
}
