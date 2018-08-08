package com.example.valdir.appitarare.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.model.Advertisement;
import com.example.valdir.appitarare.ui.fragments.ImgAdvFragment;
import com.example.valdir.appitarare.ui.fragments.MapsFragment;

/**
 * Created by VALDIR on 13/07/2018.
 * **Propaganda, anúncio, publicidade = padrão
 */

public class AdvertisementActivity extends AppCompatActivity {

    private ImgAdvFragment imgAdvFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        final Advertisement advertisement = getAdvertisementValue();

        loadFragmentAdv(advertisement.getLatitude(), advertisement.getLongitude(), advertisement.getTitulo());

        this.setTitle(advertisement.getTitulo());

        TextView descAnuncio = findViewById(R.id.tv_desc_anuncio);
        TextView wifiAnuncio = findViewById(R.id.tv_contains_wifi);
        TextView whatsAnuncio = findViewById(R.id.tv_contains_whatsap);
        TextView tituloAnuncio = findViewById(R.id.tv_title_advertisement);
        TextView telContato = findViewById(R.id.tv_tel_contato_advertisement);
        TextView formasPagamento = findViewById(R.id.tv_formas_pag_advertisement);
        TextView horarAtendimento = findViewById(R.id.tv_horafuncio_advertisement);

        tituloAnuncio.setText(advertisement.getTitulo());
        telContato.setText(advertisement.getTelContato());
        descAnuncio.setText(advertisement.getDescricao());
        formasPagamento.setText(advertisement.getFormasPagamento());
        horarAtendimento.setText(advertisement.getHorarAtendimento());

        wifiAnuncio.setText(getStringBooleanPrompt(advertisement.getWifi()));
        whatsAnuncio.setText(getStringBooleanPrompt(advertisement.getWhatsApp()));


        telContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("tel:" + advertisement.getTelContato());

                Intent intent = new Intent(Intent.ACTION_DIAL, uri);

                startActivity(intent);
            }
        });
    }

    private Advertisement getAdvertisementValue() {
        Advertisement advertisement = new Advertisement();

        Intent intentStringClicked = getIntent();

        if (intentStringClicked.hasExtra(Intent.EXTRA_TEXT)) {
            advertisement = intentStringClicked.getParcelableExtra(Intent.EXTRA_TEXT);
        }

        return advertisement;
    }

    private String getStringBooleanPrompt(int value) {
        return (value > 0) ?
                getString(R.string.prompt_yes) : getString(R.string.prompt_no);
    }

    public void loadFragmentAdv(double latitude, double longitude, String tituloAdv) {

        imgAdvFragment = new ImgAdvFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.frag_adv, imgAdvFragment, getString(R.string.TAG_FRAGMENT_IMAGE));

        Bundle bundle = new Bundle();

        bundle.putDouble(getString(R.string.KEY_LATITUDE), latitude);
        bundle.putDouble(getString(R.string.KEY_LONGITUDE), longitude);
        bundle.putString(getString(R.string.key_title_Adv), tituloAdv);


        MapsFragment mapsFrag = new MapsFragment();
        mapsFrag.setArguments(bundle);

        fragmentTransaction.add(R.id.container, mapsFrag, getString(R.string.TAG_FRAGMENT_MAPS));

        fragmentTransaction.commit();

    }

}
