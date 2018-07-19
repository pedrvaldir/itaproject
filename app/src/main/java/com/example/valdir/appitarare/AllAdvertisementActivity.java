package com.example.valdir.appitarare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.valdir.appitarare.data.AdvertisePreferences;
import com.example.valdir.appitarare.data.TestUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class AllAdvertisementActivity extends AppCompatActivity  {

    private AdvertAdapter mAdapterAnun;
    private RecyclerView mRecyViewAnun;
    private String opcClicked;
    private Context mContext;
    private ArrayList<Advertisement> listaAdvertisements = new ArrayList<>();
    public static DatabaseReference eventReference;
    private ArrayList<Advertisement> mListAnunc;
    public int countList;
    public  Toast mToast;
    private AdvertAdapter.ListItemAnunClickListener mClickListener;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("anuncios", listaAdvertisements);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_advertisement);
        mContext = this;
        Intent intentStringClicked = getIntent();

        if (intentStringClicked.hasExtra(Intent.EXTRA_TEXT)) {
            opcClicked = intentStringClicked.getStringExtra(Intent.EXTRA_TEXT);
        }

        mRecyViewAnun = (RecyclerView) findViewById(R.id.rv_advertisement);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyViewAnun.setLayoutManager(layoutManager);

        mRecyViewAnun.setHasFixedSize(true);

        mClickListener = newListener();

        loadAnunciosData();


    }

    private AdvertAdapter.ListItemAnunClickListener newListener() {
        return new AdvertAdapter.ListItemAnunClickListener(){

            @Override
            public void onListItemClick(int clickedItemIndex) {

              Advertisement atualAdvertisement = listaAdvertisements.get(clickedItemIndex);

                Intent startActivityAnuncioCompteted = new Intent(mContext, AdvertisementActivity.class);

                startActivityAnuncioCompteted.putExtra(Intent.EXTRA_TEXT, atualAdvertisement);

                startActivity(startActivityAnuncioCompteted);
            }
        };
    }

    private void loadAnunciosData() {

         final long[] childCount = {0};

        eventReference = FirebaseDatabase.getInstance().getReference();

        eventReference.child("anuncio").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapShot : dataSnapshot.getChildren()) {
                    Advertisement adv = objSnapShot.getValue(Advertisement.class);
                    childCount[0]++;
                    listaAdvertisements.add(adv);
                }

                if (childCount[0] == dataSnapshot.getChildrenCount()){

                    loadAdvertisement();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item;
        getMenuInflater().inflate(R.menu.discovery, menu);

        Boolean popularChecked = AdvertisePreferences.getPreferedOrderAvaliad(this);

        if (popularChecked) {
            item = menu.findItem(R.id.action_check_avalua);
        } else {
            item = menu.findItem(R.id.action_check_default);
        }

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
                OrderList();
                AdvertisePreferences.setPreferedOrderAvaliad(this, id);
                break;
            case R.id.action_refresh:
                loadAdvertisement();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OrderList(){

       Boolean orderbyMostAval = AdvertisePreferences.getPreferedOrderAvaliad(this);

        if (orderbyMostAval != true) {
            Collections.sort(listaAdvertisements, new OrderAdvertisement());
        }else {
            Collections.sort(listaAdvertisements, new OrderAdvertisementAlfa());
        }

        loadAdvertisement();

    }

    class OrderAdvertisement implements Comparator<Advertisement>{
        public int compare(Advertisement o1, Advertisement o2){
            if (o1.getAvaliado() < o2.getAvaliado()) return +1;
            else if (o1.getAvaliado() > o2.getAvaliado()) return -1;
            else return 0;
        }
    }

    class OrderAdvertisementAlfa implements Comparator<Advertisement>{
        public int compare(Advertisement adv, Advertisement adv2){
            return adv.getmTitulo().compareTo(adv2.getmTitulo());
        }
    }

    private void loadAdvertisement(){

        mAdapterAnun = new AdvertAdapter(mClickListener, listaAdvertisements);

        mRecyViewAnun.setAdapter(mAdapterAnun);
    }
}
