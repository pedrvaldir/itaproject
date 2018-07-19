package com.example.valdir.appitarare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by VALDIR on 13/07/2018.
 * **Propaganda, anúncio, publicidade = padrão
 */

public class AdvertisementActivity extends AppCompatActivity {

    private Advertisement clickedAdvertisement;
    private int CONSTAINS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        Intent intentStringClicked = getIntent();

        if (intentStringClicked.hasExtra(Intent.EXTRA_TEXT)){
            clickedAdvertisement = intentStringClicked.getParcelableExtra(Intent.EXTRA_TEXT);
        }

        //    mCursor.moveToPosition(Integer.valueOf());

           String toastMessage = "Item #" + clickedAdvertisement.getmTitulo() + " clicked.";

        Toast mToast;
         mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

          mToast.show();


        TextView tituloAnuncio = (TextView)findViewById(R.id.tv_title_advertisement);
        TextView descAnuncio = (TextView) findViewById(R.id.tv_desc_anuncio);
        TextView wifiAnuncio = (TextView) findViewById(R.id.tv_contains_wifi);
        TextView whatsAnuncio = (TextView) findViewById(R.id.tv_contains_whatsap);
        TextView horarAtendimento = (TextView)findViewById(R.id.tv_horafuncio_advertisement);
        TextView formasPagamento = (TextView)findViewById(R.id.tv_formas_pag_advertisement);
        TextView telContato = (TextView)findViewById(R.id.tv_tel_contato_advertisement);
        //  TextView avaliadoAnuncio = (TextView) findViewById(R.id.)
        //  TextView imgAnuncio = (TextView) findViewById(R.id.)


        tituloAnuncio.setText(clickedAdvertisement.getmTitulo());
        descAnuncio.setText(clickedAdvertisement.getDescricao());

        if (clickedAdvertisement.getmWifi() == CONSTAINS){
            wifiAnuncio.setText("SIM");
        }else{
            wifiAnuncio.setText("NÃO");
        }

        if (clickedAdvertisement.getmWhatsApp() == CONSTAINS){
            whatsAnuncio.setText("SIM");
        }else{
            whatsAnuncio.setText("NÃO");
        }

        horarAtendimento.setText(clickedAdvertisement.getmHorarAtendimento());
        formasPagamento.setText(clickedAdvertisement.getmFormasPagamento());
        telContato.setText(clickedAdvertisement.getmTelContato());

    }

}
