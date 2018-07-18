package com.example.valdir.appitarare.data;


import android.database.sqlite.SQLiteDatabase;

import com.example.valdir.appitarare.AdvertAdapter;
import com.example.valdir.appitarare.Advertisement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by VALDIR on 10/07/2018.
 */

public class TestUtil {

    public static DatabaseReference eventReference;
    public static ArrayList<Advertisement> listaAdvert;
    public static int childCount;

    public static void insertFakeData() {

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

    }

    public static ArrayList<Advertisement> loadAnunciosData() {

        listaAdvert = new ArrayList<>();

        childCount = 0;

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
}