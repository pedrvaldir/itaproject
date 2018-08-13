package com.example.valdir.appitarare.data;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class SettingsFirebase {
    private static FirebaseDatabase mData;
    private static FirebaseStorage mStorage;

    public static FirebaseDatabase getDataBase(){
        if (mData == null){
            mData = FirebaseDatabase.getInstance();
            mData.setPersistenceEnabled(true);
        }
        return mData;
    }
}


