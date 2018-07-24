package com.example.valdir.appitarare.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.data.AdvertisePreferences;
import com.example.valdir.appitarare.model.Advertisement;
import com.example.valdir.appitarare.ui.adapters.AdvertAdapter;
import com.example.valdir.appitarare.util.Constants;
import com.example.valdir.appitarare.util.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class AllAdvertisementActivity extends AppCompatActivity {

    private Context mContext;
    private String mCategorie;
    private ProgressBar mProgressBar;
    private AdvertAdapter mAdapterAnun;
    private RecyclerView mRecyViewAnun;
    private ArrayList<Advertisement> mListAdvertisement = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_advertisement);

        if (getIntent().getExtras() != null) {
            mCategorie = getIntent().getExtras().getString(Intent.EXTRA_TEXT);
            this.setTitle(mCategorie);
        }

        mContext = this;
        mProgressBar = findViewById(R.id.progressBar);

        initializeRecyclerView();

        loadAnunciosData();
    }

    private void initializeRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyViewAnun = findViewById(R.id.rv_advertisement);
        mRecyViewAnun.setLayoutManager(layoutManager);
        mRecyViewAnun.setHasFixedSize(true);
    }

    private AdvertAdapter.ListItemAnunClickListener newListener() {

        return new AdvertAdapter.ListItemAnunClickListener() {

            @Override
            public void onListItemClick(int clickedItemIndex) {

                Advertisement atualAdvertisement = mListAdvertisement.get(clickedItemIndex);

                Intent startActivityAnuncioCompteted =
                        new Intent(mContext, AdvertisementActivity.class);

                startActivityAnuncioCompteted.putExtra(Intent.EXTRA_TEXT, atualAdvertisement);

                startActivity(startActivityAnuncioCompteted);
            }
        };
    }

    private void loadAnunciosData() {
        final long[] childCount = {0};

        DatabaseReference eventReference = FirebaseDatabase.getInstance().getReference();
        eventReference
                .child(Constants.CHILD_NAME_ANUNCIOS)
                .child(Constants.CHILD_NAME_CATEGORIES)
                .child(mCategorie)
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mListAdvertisement.clear();

                        for (DataSnapshot objSnapShot : dataSnapshot.getChildren()) {
                            Advertisement adv = objSnapShot.getValue(Advertisement.class);
                            childCount[0]++;
                            mListAdvertisement.add(adv);
                        }

                        if (childCount[0] == dataSnapshot.getChildrenCount()) {
                            setLoading(false);
                            OrderList();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            mRecyViewAnun.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mRecyViewAnun.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item;
        getMenuInflater().inflate(R.menu.discovery, menu);

        Boolean popularChecked = AdvertisePreferences.getPreferredOrderRated(this);

        item = (popularChecked) ?
                menu.findItem(R.id.action_check_avalua) :
                menu.findItem(R.id.action_check_default);

        item.setChecked(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_check_avalua:
                OrderList();
            case R.id.action_check_default:
                item.setChecked(!item.isChecked());
                AdvertisePreferences.setPreferredOrderRated(this, id);
                OrderList();
                break;
            case R.id.action_refresh:
                loadAdvertisement();
                break;
            case R.id.action_add_category:
                Utils.FakeDataAdvertisement(mContext, mCategorie, false, mProgressBar, mRecyViewAnun);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OrderList() {
        Boolean orderbyMostAval = AdvertisePreferences.getPreferredOrderRated(this);

        Collections.sort(mListAdvertisement, orderbyMostAval ?
                new OrderAdvertisement() : new OrderAdvertisementAlfa());

        loadAdvertisement();

        mAdapterAnun.notifyDataSetChanged();
    }

    private void loadAdvertisement() {
        if(mAdapterAnun == null){
            mAdapterAnun = new AdvertAdapter(newListener(), mListAdvertisement);
            mRecyViewAnun.setAdapter(mAdapterAnun);
        } else{
            mAdapterAnun.notifyDataSetChanged();
        }
    }

    class OrderAdvertisement implements Comparator<Advertisement> {
        public int compare(Advertisement o1, Advertisement o2) {
            if (o1.getAvaliado() < o2.getAvaliado()) return +1;
            else if (o1.getAvaliado() > o2.getAvaliado()) return -1;
            else return 0;
        }
    }

    class OrderAdvertisementAlfa implements Comparator<Advertisement> {
        public int compare(Advertisement adv, Advertisement adv2) {
            return adv.getTitulo().compareTo(adv2.getTitulo());
        }
    }
}
