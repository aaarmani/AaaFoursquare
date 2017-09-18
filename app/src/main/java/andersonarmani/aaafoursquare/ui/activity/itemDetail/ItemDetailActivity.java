package andersonarmani.aaafoursquare.ui.activity.itemDetail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import andersonarmani.aaafoursquare.FoursquareApplication;
import andersonarmani.aaafoursquare.R;
import andersonarmani.aaafoursquare.api.model.VenueRequest;
import andersonarmani.aaafoursquare.repository.FoursquareRepository;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ItemDetailActivity extends AppCompatActivity {
    public static final String TAG = ItemDetailActivity.class.getSimpleName();
    public static final String ITEM_ID = "item_id";
    @Inject
    FoursquareRepository foursquareRepository;
    private String mItemId;

    private TextView mTvTitle;
    private TextView mTvSubTitle;
    private TextView mTvRanking;

    private TextView mTvTitle2;
    private TextView mTvText2;

    private ImageView mIvItemImage;

    private VenueRequest mVenueRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        FoursquareApplication.getsAppComponent().inject(this);

        mTvTitle = (TextView) findViewById(R.id.tv_card1_title);
        mTvSubTitle = (TextView) findViewById(R.id.tv_card1_subtitle);
        mTvRanking = (TextView) findViewById(R.id.tv_card1_rank);

        mTvTitle2 = (TextView) findViewById(R.id.tv_card2_title);
        mTvText2 = (TextView) findViewById(R.id.tv_card2_text);

        mIvItemImage = (ImageView) findViewById(R.id.item_image);

        Button btnWebsite = (Button) findViewById(R.id.btnWebsite);
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mVenueRequest == null) {
                    Log.e(TAG, "Venue request NULL");
                    return;
                }
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(mVenueRequest.getResponse().getVenue().getUrl()));
                    startActivity(browserIntent);
                } catch (NullPointerException e) {
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(getBaseContext(), "URL not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                mItemId = null;
            } else {
                mItemId = extras.getString(ITEM_ID);
            }
        } else {
            mItemId = (String) savedInstanceState.getSerializable(ITEM_ID);
        }

        getItemData(mItemId);
    }

    private void getItemData(String mItemId) {
        foursquareRepository.getVenueRequest(mItemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<VenueRequest>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(@NonNull VenueRequest venueRequest) {
                        Log.d(TAG, "onSuccess");
                        mVenueRequest = venueRequest;
                        populateFields(venueRequest);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError = " + e);
                        showError(e);
                    }
                });
    }

    private void populateFields(VenueRequest venueRequest) {
        if(venueRequest == null) {
            Log.e(TAG, "Venue request NULL");
            return;
        }

        mTvTitle.setText(venueRequest.getResponse().getVenue().getName());
        mTvSubTitle.setText(venueRequest.getResponse().getVenue().getCategories().get(0).getName());
        mTvRanking.setText(String.valueOf(venueRequest.getResponse().getVenue().getRating()));

        mTvText2.setText(venueRequest.getResponse().getVenue().getDescription());
        //Load image
        try {
            String strImage = venueRequest.getResponse().getVenue().getTips()
                    .getGroups().get(0).getItems().get(0).getPhotourl();

            if (strImage != null) {
                Picasso.with(getBaseContext()).load(strImage).into(mIvItemImage);
            }
        } catch(Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void showError(Throwable e) {
        Log.e(TAG, e.getMessage());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(ITEM_ID, mItemId);
        super.onSaveInstanceState(outState);
    }
}
