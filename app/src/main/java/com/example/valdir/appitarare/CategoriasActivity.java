package com.example.valdir.appitarare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class CategoriasActivity extends AppCompatActivity implements CategAdapter.ListItemClickListener{

    private Toast mToast;
    private ArrayList<String> listCateg;
    private RecyclerView lvCateg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        loadCategoriasData();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }

        String OpcClicked = listCateg.get(clickedItemIndex).toString();

        String toastMessage = "Item #" + listCateg.get(clickedItemIndex).toString() + " clicked.";

        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

        Intent startCategorActivity = new Intent(this, AnunciosActivity.class);

        startCategorActivity.putExtra(Intent.EXTRA_TEXT, OpcClicked);

        startActivity(startCategorActivity);
    }

    private void loadCategoriasData() {

        lvCateg = (RecyclerView) findViewById(R.id.rv_categorias);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lvCateg.setLayoutManager(layoutManager);

        listCateg = new ArrayList<>();
        listCateg.add("BARES");
        listCateg.add("HOTEIS");
        listCateg.add("SUPERMERCADOS");
        listCateg.add("LIVRARIAS");
        listCateg.add("CHURRASCARIAS");
        listCateg.add("POSTOS");
        listCateg.add("CONVENIENCIA");
        listCateg.add("MANICURI");
        listCateg.add("MECANICA");
        listCateg.add("PERFUMARIA");
        listCateg.add("ATACADO");
        listCateg.add("LAZER");
        listCateg.add("RESTAURANTES");
        listCateg.add("EVENTOS");

        lvCateg.setHasFixedSize(true);

        Collections.sort(listCateg, new orderCategAlfa());

        CategAdapter adapterCateg = new CategAdapter(this, listCateg);

        lvCateg.setAdapter(adapterCateg);
    }

    class orderCategAlfa implements Comparator<String> {

        @Override
        public int compare(String anuncio, String t1) {
            return anuncio.compareTo(t1);
        }
    }
}

