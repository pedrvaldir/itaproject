package com.example.valdir.appitarare.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.ui.adapters.CategAdapter;
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

public class CategoryActivity extends AppCompatActivity implements CategAdapter.ListItemClickListener {

    private Context mContext;
    private ProgressBar mProgressBar;
    private CategAdapter mAdapterCateg;
    private RecyclerView mRecyclerView;
    private String mCategoryName = null;
    private ArrayList<String> mListCateg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mContext = this;
        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.rv_category);

        loadCategoriesData();
    }

    private void loadCategoriesData() {

        setLoading(true);

        final long[] childCount = {0};

        DatabaseReference eventReference = FirebaseDatabase.getInstance().getReference();

        eventReference
                .child(Constants.CHILD_NAME_ANUNCIOS)
                .child(Constants.CHILD_NAME_CATEGORIES)
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        childCount[0] = 0;
                        ArrayList<String> newList = new ArrayList<>();

                        for (DataSnapshot objSnapShot : dataSnapshot.getChildren()) {
                            childCount[0]++;
                            newList.add(objSnapShot.getKey());
                        }

                        if (childCount[0] == dataSnapshot.getChildrenCount()) {
                            setLoading(false);
                            showCategories(newList);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void notifyDataChanged(ArrayList<String> newList) {
        mListCateg.clear();
        mListCateg.addAll(newList);
        mAdapterCateg.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_add_category:
                buildInputCategoryName();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void buildInputCategoryName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setTitle(getString(R.string.prompt_category_name));
        builder.setView(input);
        builder.setPositiveButton(getString(R.string.prompt_insert), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCategoryName = input.getText().toString();
                FakeDataAdvertisement();
            }
        });

        builder.show();
    }

    private static DatabaseReference buildReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public void FakeDataAdvertisement() {
        Utils.FakeDataAdvertisement(mContext,mCategoryName, true, mProgressBar, mRecyclerView);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent startCategorActivity = new Intent(this, AllAdvertisementActivity.class);

        startCategorActivity.putExtra(Intent.EXTRA_TEXT, mListCateg.get(clickedItemIndex));

        startActivity(startCategorActivity);
    }

    private void showCategories(ArrayList<String> newList) {
        if (mAdapterCateg == null) {
            initializeRecyclerView(newList);
        } else {
            notifyDataChanged(newList);
        }
    }

    private void initializeRecyclerView(ArrayList<String> newList) {
        RecyclerView lvCateg = findViewById(R.id.rv_category);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        lvCateg.setLayoutManager(layoutManager);
        lvCateg.setHasFixedSize(true);

        mListCateg = newList;

        Collections.sort(mListCateg, new orderCategAlfa());

        mAdapterCateg = new CategAdapter(this, mListCateg);

        lvCateg.setAdapter(mAdapterCateg);
    }

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            mRecyclerView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    class orderCategAlfa implements Comparator<String> {
        @Override
        public int compare(String anuncio, String t1) {
            return anuncio.compareTo(t1);
        }
    }
}

