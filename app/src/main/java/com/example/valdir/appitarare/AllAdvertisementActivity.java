package com.example.valdir.appitarare;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valdir.appitarare.data.AdvertiseContract;
import com.example.valdir.appitarare.data.AdvertisePreferences;
import com.example.valdir.appitarare.data.AdvertiseDbHelper;
import com.example.valdir.appitarare.data.TestUtil;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class AllAdvertisementActivity extends AppCompatActivity implements AdvertAdapter.ListItemAnunClickListener{

    private AdvertAdapter mAdapterAnun;
    private RecyclerView mRecyViewAnun;
    private String opcClicked;
    private Toast mToast;
    private DatabaseUtils dataBaseUtils;
    private ArrayList<Advertisement> listaAdvertisements = new ArrayList<>();
    private SQLiteDatabase mDb;
    private Cursor mCursor;
    private static Advertisement mAdvertisement;
    DatabaseReference eventReference;

    private static final String ADVERTISEMENT = "advertisement";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("anuncios", listaAdvertisements);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_advertisement);

        Intent intentStringClicked = getIntent();

        if (intentStringClicked.hasExtra(Intent.EXTRA_TEXT)){
            opcClicked = intentStringClicked.getStringExtra(Intent.EXTRA_TEXT);
        }

        mRecyViewAnun = (RecyclerView) findViewById(R.id.rv_advertisement);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyViewAnun.setLayoutManager(layoutManager);

        mRecyViewAnun.setHasFixedSize(true);

        AdvertiseDbHelper dbHelper = new AdvertiseDbHelper(this);

        mDb = dbHelper.getWritableDatabase();

        TestUtil.insertFakeData(mDb);

        mCursor = retornoTodosAnuncios();

        loadAnunciosData(mCursor);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }

        mCursor.moveToPosition(clickedItemIndex);

        String toastMessage = "Item #" + mCursor.getString(mCursor.getColumnIndex(AdvertiseContract.AnuncioEntrada.COLUNA_TITULO)) + " clicked.";

        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

        Intent startActivityAnuncioCompteted = new Intent(this, AdvertisementActivity.class);

        startActivityAnuncioCompteted.putExtra(Intent.EXTRA_TEXT, mCursor.getString(mCursor.getColumnIndex(AdvertiseContract.AnuncioEntrada._ID)));

        startActivity(startActivityAnuncioCompteted);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item;
        getMenuInflater().inflate(R.menu.discovery, menu);

        Boolean popularChecked = AdvertisePreferences.getPreferedOrderAvaliad(this);

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
                AdvertisePreferences.setPreferedOrderAvaliad(this, id);
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

        eventReference.child("anuncio").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapShot : dataSnapshot.getChildren()){
                    Advertisement adv = objSnapShot.getValue(Advertisement.class);

                    listaAdvertisements.add(adv);
                }


                mAdapterAnun = new AdvertAdapter(this, listaAdvertisements);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mRecyViewAnun.setAdapter(mAdapterAnun);

    }

    private Cursor retornoTodosAnuncios(){
        return mDb.query(AdvertiseContract.AnuncioEntrada.NOME_TABELA,
                null,
                null,
                null,
                null,
                null,
                AdvertiseContract.AnuncioEntrada.COLUNA_AVALIADO
        );
    }

    public long addAnuncioTest(){

        eventReference = FirebaseDatabase.getInstance().getReference();

        Advertisement newAdv = new Advertisement();

        newAdv.setmTitulo("asdfasfd");
        newAdv.setmDescricao("desc");
        newAdv.setmFormasPagamento("dinheiro");
        newAdv.setmHorarAtendimento("8h as sd98");
        newAdv.setmWhatsApp(1);
        newAdv.setmWifi(0);
        newAdv.setmAvaliado(90);
        newAdv.setmTelContato("asdfasfdasf");
        newAdv.setmImg(12312312);

        UUID _id = UUID.randomUUID();

        eventReference.child("anuncio").child(_id.toString()).setValue(newAdv);

        ContentValues cv = new ContentValues();

        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_TITULO, "SUPERMERCADO JACKS");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_DESCRICAO, "O MELHOR LUGAR PARA VC E SUA FAMILIA");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_HORA_ATEND, "DAS 8H AS 18H, SEG A SEX");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_FORMA_PAGAMENTO, "CARTÕES: MASTERCARD, ELO, VISA");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_AVALIADO, 50);
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_TEL_CONTATO, "(15) 4567-9844");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_IMG, R.drawable.food);
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_WHATSAPP, 0);
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_WIFI, 1);

        return mDb.insert(AdvertiseContract.AnuncioEntrada.NOME_TABELA, null, cv);
    }
}
