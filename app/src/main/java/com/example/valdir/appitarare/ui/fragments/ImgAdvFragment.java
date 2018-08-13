package com.example.valdir.appitarare.ui.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.util.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImgAdvFragment extends Fragment {

    private ImageView imgAdver;
    private String imgNameAdv;


    public ImgAdvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_img_adv, container, false);

        imgAdver =(ImageView) view.findViewById(R.id.img_fragment_adv);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl(Constants.URL_IMAGE_STORAGE_FIREBASE).child("bifarma.JPG");

        //pegar tamanho da tela do celular

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        final int height = (displayMetrics.heightPixels);
        final int width = (displayMetrics.widthPixels);

        final View viewFragm = view;

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                imgAdver = (ImageView) viewFragm.findViewById(R.id.img_fragment_adv);

                Picasso.get().load(uri.toString()).resize(height, width).centerInside().into(imgAdver);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        return  view;
    }

}
