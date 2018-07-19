package com.example.valdir.appitarare.data;


import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.valdir.appitarare.AdvertAdapter;
import com.example.valdir.appitarare.Advertisement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by VALDIR on 10/07/2018.
 */

public class TestUtil{

    public static int ANUN_WI_OK = 1;
    public static int ANUN_WTSAPP_OK = 1;
    public static int ANUN_WI = 0;
    public static int ANUN_WTSAPP = 0;
    public static DatabaseReference eventReference;
    public static ArrayList<Advertisement>  listaAdvert;
    public static AdvertAdapter mAdapterAnun;
    public static int childCount;

    public static void insertFakeData() {

        eventReference = FirebaseDatabase.getInstance().getReference();

        Advertisement newAdv = new Advertisement();

        UUID _id = UUID.randomUUID();

        newAdv.setUid(_id.toString());
        newAdv.setmTitulo("EMPRESA 8");
        newAdv.setmDescricao("Venha conhecer nosso ambiente .....");
        newAdv.setmHorarAtendimento("8h as 18h");
        newAdv.setmFormasPagamento("dinheiro e cartões");
        newAdv.setmTelContato("15 3531- 1748");
        newAdv.setmAvaliado(90);
        newAdv.setmImg(12312312);
        newAdv.setmWifi(0);
        newAdv.setmWhatsApp(1);

        eventReference.child("anuncio").child(_id.toString()).setValue(newAdv);

    }

    public static ArrayList<Advertisement> loadAnunciosData() {


        listaAdvert = new ArrayList<>();

        childCount =0;

        eventReference = FirebaseDatabase.getInstance().getReference();

        eventReference.child("anuncio").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapShot : dataSnapshot.getChildren()) {
                    Advertisement adv = objSnapShot.getValue(Advertisement.class);
                    childCount++;
                    listaAdvert.add(adv);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return listaAdvert;

    }

    public void pesquisarAnun(String id){

        Query query;

        query = eventReference.child("anuncio").equalTo(id);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Advertisement ad = objSnapshot.getValue(Advertisement.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}