package com.example.valdir.appitarare.ui.fragments;

import android.content.Context;
import android.net.Uri;
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
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ImgNewsFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private ImageView imgNews;
    private String imgNameNews;
    private ProgressBar mProgressBar;

    public ImgNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        if (bundle != null) {
            imgNameNews = bundle.getString(getString(R.string.key_title_img_news));
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_img_news, container, false);

        mProgressBar = view.findViewById(R.id.progressBarNewsFragment);

        mProgressBar.setVisibility(View.VISIBLE);

        imgNews = (ImageView) view.findViewById(R.id.img_fragment_news);

        //pegar tamanho da tela do celular
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        final int height = (displayMetrics.heightPixels);
        final int width = (displayMetrics.widthPixels);

        Picasso.get().load(imgNameNews).resize(height, width).centerInside().into(imgNews, new ImageLoadedCallback(mProgressBar) {
            @Override
            public void onSuccess() {
                if (this.progressBar != null) {
                    this.progressBar.setVisibility(View.GONE);
                    imgNews.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;

    }

    private class ImageLoadedCallback implements Callback {
        ProgressBar progressBar;

        public ImageLoadedCallback(ProgressBar progBar) {
            progressBar = progBar;
        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError(Exception e) {

        }
    }
}
