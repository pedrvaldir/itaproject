package com.example.valdir.appitarare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.valdir.appitarare.data.AdvertisePreferences;
import com.example.valdir.appitarare.data.TestUtil;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class AllAdvertisementActivity extends AppCompatActivity implements AdvertAdapter.ListItemAnunClickListener {

    private AdvertAdapter mAdapterAnun;
    private RecyclerView mRecyViewAnun;
    private String opcClicked;
    private ArrayList<Advertisement> listaAdvertisements = new ArrayList<>();
    private ArrayList<Advertisement> mListAnunc;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("anuncios", listaAdvertisements);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_advertisement);

        Intent intentStringClicked = getIntent();

        if (intentStringClicked.hasExtra(Intent.EXTRA_TEXT)) {
            opcClicked = intentStringClicked.getStringExtra(Intent.EXTRA_TEXT);
        }

        mRecyViewAnun = (RecyclerView) findViewById(R.id.rv_advertisement);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyViewAnun.setLayoutManager(layoutManager);

        mRecyViewAnun.setHasFixedSize(true);

        mListAnunc = TestUtil.loadAnunciosData();

        mAdapterAnun = new AdvertAdapter(this, mListAnunc);

        mRecyViewAnun.setAdapter(mAdapterAnun);

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent startActivityAnuncioCompteted = new Intent(this, AdvertisementActivity.class);

        startActivityAnuncioCompteted.putExtra(Intent.EXTRA_TEXT, "teste");

        startActivity(startActivityAnuncioCompteted);
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
                item.setChecked(!item.isChecked());
                TestUtil.insertFakeData();
            case R.id.action_check_default:
                item.setChecked(!item.isChecked());
                AdvertisePreferences.setPreferedOrderAvaliad(this, id);
                break;
            case R.id.action_refresh:
                addAnuncioTest();
                TestUtil.insertFakeData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAnuncioTest() {
    }
}
