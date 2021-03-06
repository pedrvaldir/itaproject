package com.example.valdir.appitarare.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.valdir.appitarare.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImgAdvFragment extends Fragment {

    private ImageView imgAdver;
    private String imgNameAdv;
    private ProgressBar mProgressBar;


    public ImgAdvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Bundle bundle = getArguments();

        if (bundle != null) {
            imgNameAdv = bundle.getString(getString(R.string.key_title_img));
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_img_adv, container, false);



        mProgressBar = view.findViewById(R.id.progressBarImgFragment);

        mProgressBar.setVisibility(View.VISIBLE);

        imgAdver = (ImageView) view.findViewById(R.id.img_fragment_adv);



        //pegar tamanho da tela do celular
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        final int height = (displayMetrics.heightPixels);
        final int width = (displayMetrics.widthPixels);

        Picasso.get().load(imgNameAdv).resize(height, width).centerInside().into(imgAdver, new ImageLoadedCallback(mProgressBar) {
            @Override public void onSuccess() {
                if (this.progressBar != null) {
                    this.progressBar.setVisibility(View.GONE);
                imgAdver.setVisibility(View.VISIBLE);}
            }
        });

        return view;


    }

    private class ImageLoadedCallback implements Callback {
        ProgressBar progressBar;

        public ImageLoadedCallback(ProgressBar progBar){
            progressBar = progBar;
        } @Override public void onSuccess() {

        }

        @Override
        public void onError(Exception e) {

        }
    }

}
