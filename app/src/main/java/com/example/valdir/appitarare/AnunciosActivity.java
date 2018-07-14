package com.example.valdir.appitarare;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.valdir.appitarare.data.AnunPreferences;
import com.example.valdir.appitarare.data.AnuncioContract;
import com.example.valdir.appitarare.data.AnuncioDbHelper;
import com.example.valdir.appitarare.data.TestUtil;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class AnunciosActivity  extends AppCompatActivity implements AnunAdapter.ListItemAnunClickListener{

    private AnunAdapter mAdapterAnun;
    private RecyclerView mRecyViewAnun;
    private String opcClicked;
    private Toast mToast;

    private ArrayList<Anuncio> listaAnuncios = new ArrayList<>();
    private SQLiteDatabase mDb;
    private Cursor mCursor;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("anuncios", listaAnuncios);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        Intent intentStringClicked = getIntent();

        if (intentStringClicked.hasExtra(Intent.EXTRA_TEXT)){
            opcClicked = intentStringClicked.getStringExtra(Intent.EXTRA_TEXT);
        }

        //GridView gvAnuncios = (GridView) findViewById(R.id.gridview_anuncios);

        // StaggeredGridLayoutManager staggeredGridLayoutManager =
        //         new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyViewAnun = (RecyclerView) findViewById(R.id.rv_anuncios);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyViewAnun.setLayoutManager(layoutManager);

        mRecyViewAnun.setHasFixedSize(true);

        AnuncioDbHelper dbHelper = new AnuncioDbHelper(this);

        mDb = dbHelper.getWritableDatabase();

        TestUtil.insertFakeData(mDb);

        mCursor = retornoTodosAnuncios();

        // public Anuncio(String titulo, String descricao, String atendimento, String formasPagamento, String contato, int img, int avaliado, Boolean wifi) {

        Anuncio[] anuncios = {
                new Anuncio( "B->" + opcClicked + "  - 1","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.empresa1, 1, true, true),
                new Anuncio( "A->" + opcClicked + "  - 2","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.empresa1, 10, false, false),
                new Anuncio( "E->" + opcClicked + "  - 3","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.food, 7, false, true),
                new Anuncio( "K->" + opcClicked + "  - 4","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.food, 3, true, true),
                new Anuncio( "D->" + opcClicked + "  - 5","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.empresa1, 8, false, true),
                new Anuncio( "A->" + opcClicked + "  - 6","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.food, 6, false, true),
                new Anuncio( "H->" + opcClicked + "  - 7","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.empresa1, 0 , true, true),
                new Anuncio( "L->" + opcClicked + "  - 8","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.empresa1, 7, false, true),
                new Anuncio( "A->" + opcClicked + "  - 9","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.food, 30, true, true),
                new Anuncio( "G->" + opcClicked + "  - 10","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.empresa1, 55, false, true),
                new Anuncio( "Z->" + opcClicked + "  - 11","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.food, 70, true, false),
                new Anuncio( "I->" + opcClicked + "  - 12","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.food, 10, false, true),
                new Anuncio( "J->" + opcClicked + "  - 13", "Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.empresa1, 4, false, true),
                new Anuncio( "Y->" + opcClicked + "  - 14","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.food, 8, false, true),
                new Anuncio( "F->" + opcClicked + "  - 15","Venha conhecer nosso espaço! \n O Melhor da região, lugar ótimo para você e sua familia",
                        " DAS 8H AS 19H","Dinheiro - Cartões (ELO-MASTERCARD)","CONTATO:  15 3531-1287", R.drawable.empresa1, 0, true, true),
        };
        /*
        if (savedInstanceState == null || !savedInstanceState.containsKey("anuncios")){
            listaAnuncios = new ArrayList<Anuncio>(Arrays.asList(anuncios));
        }
        else{
            listaAnuncios = savedInstanceState.getParcelableArrayList("anuncios");
        }
        */

        loadAnunciosData(mCursor);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }

        mCursor.moveToPosition(clickedItemIndex);

        String toastMessage = "Item #" + mCursor.getString(mCursor.getColumnIndex(AnuncioContract.AnuncioEntrada.COLUNA_TITULO)) + " clicked.";

        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

        Intent startActivityAnuncioCompteted = new Intent(this, AnuncioActivity.class);

        startActivityAnuncioCompteted.putExtra(Intent.EXTRA_TEXT, mCursor.getString(mCursor.getColumnIndex(AnuncioContract.AnuncioEntrada._ID)));

        startActivity(startActivityAnuncioCompteted);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item;
        getMenuInflater().inflate(R.menu.discovery, menu);

        Boolean popularChecked = AnunPreferences.getPreferedOrderAvaliad(this);

        if (popularChecked){
            item = menu.findItem(R.id.action_check_avalua);
        }else {
            item = menu.findItem(R.id.action_check_default);
        }

        item.setChecked(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_check_avalua:
                item.setChecked(!item.isChecked());
                loadAnunciosData(mCursor);
            case R.id.action_check_default:
                item.setChecked(!item.isChecked());
                AnunPreferences.setPreferedOrderAvaliad(this, id);
                loadAnunciosData(mCursor);
                break;
            case R.id.action_refresh:
                addAnuncioTest();
                mCursor = retornoTodosAnuncios();
                loadAnunciosData(mCursor);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void loadAnunciosData(Cursor cursor){

        // Boolean orderByAvaliad = AnunPreferences.getPreferedOrderAvaliad(this);

        mAdapterAnun = new AnunAdapter(this, mCursor);

        AnunAdapter mAdapterAnun = new AnunAdapter(this, mCursor);

        mRecyViewAnun.setAdapter(mAdapterAnun);

    }

    class orderAnuncios implements Comparator<Anuncio> {

        @Override
        public int compare(Anuncio anuncio, Anuncio t1) {
            if (anuncio.getAvaliado() < t1.getAvaliado())return  +1;
            else if(anuncio.getAvaliado() > t1.getAvaliado()) return -1;
            else return 0;
        }
    }

    class orderAnunciosAlfa implements Comparator<Anuncio>{

        @Override
        public int compare(Anuncio anuncio, Anuncio t1) {
            return anuncio.getTitulo().compareTo(t1.getTitulo());
        }
    }

    private Cursor retornoTodosAnuncios(){
        return mDb.query(AnuncioContract.AnuncioEntrada.NOME_TABELA,
                null,
                null,
                null,
                null,
                null,
                AnuncioContract.AnuncioEntrada.COLUNA_AVALIADO
        );
    }

    public long addAnuncioTest(){

        ContentValues cv = new ContentValues();

        cv.put(AnuncioContract.AnuncioEntrada.COLUNA_TITULO, "SUPERMERCADO LIMA");
        cv.put(AnuncioContract.AnuncioEntrada.COLUNA_DESCRICAO, "O MELHOR LUGAR PARA VC E SUA FAMILIA");
        cv.put(AnuncioContract.AnuncioEntrada.COLUNA_HORA_ATEND, "DAS 8H AS 18H, SEG A SEX");
        cv.put(AnuncioContract.AnuncioEntrada.COLUNA_FORMA_PAGAMENTO, "CARTÕES: MASTERCARD, ELO, VISA");
        cv.put(AnuncioContract.AnuncioEntrada.COLUNA_AVALIADO, 50);
        cv.put(AnuncioContract.AnuncioEntrada.COLUNA_TEL_CONTATO, "(15) 4567-9844");
        cv.put(AnuncioContract.AnuncioEntrada.COLUNA_IMG, R.drawable.food);
        cv.put(AnuncioContract.AnuncioEntrada.COLUNA_WHATSAPP, 0);
        cv.put(AnuncioContract.AnuncioEntrada.COLUNA_WIFI, 1);

        return mDb.insert(AnuncioContract.AnuncioEntrada.NOME_TABELA, null, cv);
    }


}
