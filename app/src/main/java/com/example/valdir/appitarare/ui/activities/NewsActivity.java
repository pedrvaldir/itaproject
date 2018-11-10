package com.example.valdir.appitarare.ui.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.model.News;
import com.example.valdir.appitarare.ui.fragments.ImgNewsFragment;

public class NewsActivity extends AppCompatActivity {

    private ImgNewsFragment imgNewsFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        final News news = getNewsValue();

        loadFragmentNews(news.getImage());

        TextView txtTitleNews = findViewById(R.id.txt_title_news);
        TextView txtDescNews = findViewById(R.id.txt_description_news);
        TextView txtDateNews = findViewById(R.id.txt_date_news);
        TextView txtAuthorSource = findViewById(R.id.txt_fonte_news);

        toChargeNewsActivity(news, txtTitleNews, txtDescNews, txtDateNews, txtAuthorSource);

    }

    private void toChargeNewsActivity(News news, TextView txtTitleNews, TextView txtDescNews, TextView txtDateNews, TextView txtAuthorSource) {
        txtTitleNews.setText(news.getTitle());
        txtDescNews.setText(news.getDescription());
        txtDateNews.setText(news.getDatePublication());
        txtAuthorSource.setText(news.getSourceAuthor());
    }


    private News getNewsValue() {
        News news = new News();

        Intent intentStringClicked = getIntent();

        if (intentStringClicked.hasExtra(Intent.EXTRA_TEXT)) {
            news = intentStringClicked.getParcelableExtra(Intent.EXTRA_TEXT);
        }

        return news;
    }

    private void loadFragmentNews(String image) {

        Bundle bundleImg = new Bundle();
        bundleImg.putString(getString(R.string.key_title_img_news), image);

        imgNewsFragment = new ImgNewsFragment();
        imgNewsFragment.setArguments(bundleImg);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if (!image.equals("")) {
            fragmentTransaction.add(R.id.frag_news, imgNewsFragment, getString(R.string.TAG_FRAGMENT_NEWS));

        }

        fragmentTransaction.commit();

    }
}
