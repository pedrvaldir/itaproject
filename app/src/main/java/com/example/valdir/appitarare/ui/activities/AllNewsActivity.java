package com.example.valdir.appitarare.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.data.AdvertisePreferences;
import com.example.valdir.appitarare.model.News;
import com.example.valdir.appitarare.ui.adapters.NewsAdapter;
import com.example.valdir.appitarare.util.Constants;
import com.example.valdir.appitarare.util.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllNewsActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private Context mContext;
    private RecyclerView mRecyViewNews;
    private NewsAdapter mNewsAdapter;
    private ArrayList<News> mListNews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);

        mContext = this;

        mProgressBar = findViewById(R.id.progressBarNews);

        initializeRecyclerView();

        loadNewsData();


    }

    private NewsAdapter.ListItemNewsClickListener newListener() {

        return new NewsAdapter.ListItemNewsClickListener() {

            @Override
            public void onListItemClick(int clickedItemIndex) {

                News atualNews = mListNews.get(clickedItemIndex);

                Intent startActivityNewsCompteted =
                        new Intent(mContext, NewsActivity.class);

                startActivityNewsCompteted.putExtra(Intent.EXTRA_TEXT, atualNews);

                startActivity(startActivityNewsCompteted);
            }
        };
    }

    private void loadNewsData() {


        setLoading(true);

        final long[] childCount = {0};

        DatabaseReference eventReference = FirebaseDatabase.getInstance().getReference();

        eventReference
                .child(Constants.CHILD_NAME_NOTICIAS)
                .child(Constants.CHILD_NAME_PUBLICACAO)
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mListNews.clear();

                        for (DataSnapshot objSnapShot : dataSnapshot.getChildren()) {
                            News adv = objSnapShot.getValue(News.class);
                            childCount[0]++;
                            mListNews.add(adv);

                            Log.e("Tag - allNews", "adv" + adv.getTitle());
                        }

                        if (childCount[0] == dataSnapshot.getChildrenCount()) {
                            setLoading(false);
                            loadNews();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }


    private void initializeRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyViewNews = findViewById(R.id.rv_news);
        mRecyViewNews.setLayoutManager(layoutManager);
        mRecyViewNews.setHasFixedSize(true);
    }


    private void setLoading(boolean isLoading) {
        if (isLoading) {
            mRecyViewNews.setVisibility(View.GONE);
            //     //    mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mRecyViewNews.setVisibility(View.VISIBLE);
            //    mProgressBar.setVisibility(View.GONE);
        }
    }


    private void loadNews() {
        if (mNewsAdapter == null) {
            mNewsAdapter = new NewsAdapter(newListener(), mListNews);
            mRecyViewNews.setAdapter(mNewsAdapter);
        } else {
            mNewsAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            /* Retirado opc avaliado
            case R.id.action_check_avalua:
                OrderList();
            case R.id.action_check_default:
                item.setChecked(!item.isChecked());
                AdvertisePreferences.setPreferredOrderRated(this, id);

                break;
            case R.id.action_refresh:
                loadAdvertisement();
                break;
                */
            case R.id.action_add_category:
                Utils.FakeDataNews(mContext, false, mProgressBar, mRecyViewNews);
                break;
        }

        loadNews();

        mNewsAdapter.notifyDataSetChanged();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem item;

        getMenuInflater().inflate(R.menu.discovery, menu);

        Boolean popularChecked = AdvertisePreferences.getPreferredOrderRated(this);

        return true;
    }
}