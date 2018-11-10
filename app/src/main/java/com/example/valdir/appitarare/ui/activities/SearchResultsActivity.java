package com.example.valdir.appitarare.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.data.SettingsFirebase;
import com.example.valdir.appitarare.model.Advertisement;
import com.example.valdir.appitarare.ui.adapters.AdvertAdapter;
import com.example.valdir.appitarare.util.Constants;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class SearchResultsActivity extends AppCompatActivity {

    private RecyclerView mRecyViewAnun;
    private Context mContext;
    private String mCategorie;
    private ProgressBar mProgressBar;
    private TextView mTextSearchNull;
    private ArrayList<Advertisement> mListAdvertisement = new ArrayList<>();
    private AdvertAdapter mAdapterAnun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results2);

        mContext = this;
        mProgressBar = findViewById(R.id.progressBar_search);
        mTextSearchNull = findViewById(R.id.textSearchNull);

        mListAdvertisement.clear();

        initializeRecyclerView();

        handleIntent(getIntent());
    }


    private void handleIntent(Intent intent) {


        String pesquisa = (String) intent.getSerializableExtra("SEARCH_ICON");


        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            if (getIntent().getExtras() != null) {
                mCategorie = getIntent().getExtras().getString(Intent.EXTRA_TEXT);
                this.setTitle(query);
            }

            filterData(query);
        }else if(pesquisa!=null){

            filterData(pesquisa);
        }
    }

    private void initializeRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyViewAnun = findViewById(R.id.rv_advertisement_search);
        mRecyViewAnun.setLayoutManager(layoutManager);
        mRecyViewAnun.setHasFixedSize(true);
    }

    public void filterData(final String q) {

        final DatabaseReference eventReference = SettingsFirebase.getDataBase()
                .getReference(Constants.CHILD_NAME_ANUNCIOS)
                .child(Constants.CHILD_NAME_CATEGORIES);

        searchCategorie(q, eventReference);

        searchSingle(q, eventReference);
    }

    private void searchSingle(final String q, final DatabaseReference eventReference) {

        final long[] childCount = {0};

        eventReference.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot objSnapShot : dataSnapshot.getChildren()) {
                    Advertisement adv = objSnapShot.getValue(Advertisement.class);

                    childCount[0]++;

                    if (adv.getTitulo().toLowerCase().contains(q.toLowerCase()) ||
                            adv.getDescricao().toLowerCase().contains(q.toLowerCase())) {

                        mListAdvertisement.add(adv);
                        mTextSearchNull.setVisibility(View.GONE);
                    }

                    if (childCount[0] == dataSnapshot.getChildrenCount()) {

                        setLoading(false);
                        loadAdvertisement();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void searchCategorie(final String q, final DatabaseReference eventReference) {

        final long[] childCount = {0};

        mListAdvertisement.clear();

        eventReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (final DataSnapshot objSnapShot1 : dataSnapshot.getChildren()) {
                    if (objSnapShot1.getKey().toLowerCase().contains(q.toLowerCase())) {

                        eventReference.child(objSnapShot1.getKey()).addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot objSnapShot : dataSnapshot.getChildren()) {
                                    Advertisement adv = objSnapShot.getValue(Advertisement.class);
                                    childCount[0]++;


                                    boolean valid = false;

                                    for (int i = 0; i < mListAdvertisement.size(); i++) {
                                        if (mListAdvertisement.get(i).getTitulo().toLowerCase().equals(adv.getTitulo().toLowerCase())) {

                                            valid = true;
                                        }
                                    }
                                    if (!valid)
                                        mListAdvertisement.add(adv);
                                }

                                if (childCount[0] == dataSnapshot.getChildrenCount()) {
                                    setLoading(false);
                                    loadAdvertisement();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
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
            mTextSearchNull.setVisibility(View.GONE);
            mRecyViewAnun.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void loadAdvertisement() {

        if (mAdapterAnun == null) {
            mAdapterAnun = new AdvertAdapter(newListener(), mListAdvertisement, Constants.CHILD_NAME_CATEGORIES);
            mRecyViewAnun.setAdapter(mAdapterAnun);
        } else {
            mAdapterAnun.notifyDataSetChanged();
        }

        if (mListAdvertisement.isEmpty())
            mTextSearchNull.setVisibility(View.VISIBLE);
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

}
