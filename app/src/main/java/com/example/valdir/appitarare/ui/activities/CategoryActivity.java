package com.example.valdir.appitarare.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.ui.adapters.CategAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class CategoryActivity extends AppCompatActivity implements CategAdapter.ListItemClickListener {

    private Toast mToast;
    private ArrayList<String> mListCateg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        loadCategoriasData();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent startCategorActivity = new Intent(this, AllAdvertisementActivity.class);

        startCategorActivity.putExtra(Intent.EXTRA_TEXT, clickedItemIndex);

        startActivity(startCategorActivity);
    }

    private void loadCategoriasData() {

        RecyclerView lvCateg = findViewById(R.id.rv_category);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        lvCateg.setLayoutManager(layoutManager);

        mListCateg = new ArrayList<>();
        mListCateg.add("BARES");
        mListCateg.add("HOTEIS");
        mListCateg.add("SUPERMERCADOS");
        mListCateg.add("LIVRARIAS");
        mListCateg.add("CHURRASCARIAS");
        mListCateg.add("POSTOS");
        mListCateg.add("CONVENIENCIA");
        mListCateg.add("MANICURI");
        mListCateg.add("MECANICA");
        mListCateg.add("PERFUMARIA");
        mListCateg.add("ATACADO");
        mListCateg.add("LAZER");
        mListCateg.add("RESTAURANTES");
        mListCateg.add("EVENTOS");

        lvCateg.setHasFixedSize(true);

        Collections.sort(mListCateg, new orderCategAlfa());

        CategAdapter adapterCateg = new CategAdapter(this, mListCateg);

        lvCateg.setAdapter(adapterCateg);
    }

    class orderCategAlfa implements Comparator<String> {

        @Override
        public int compare(String anuncio, String t1) {
            return anuncio.compareTo(t1);
        }
    }
}

