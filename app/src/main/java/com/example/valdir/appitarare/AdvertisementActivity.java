package com.example.valdir.appitarare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by VALDIR on 13/07/2018.
 * **Propaganda, anúncio, publicidade = padrão
 */

public class AdvertisementActivity extends AppCompatActivity {

    private String clickedItemIndex;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        Intent intentStringClicked = getIntent();

        if (intentStringClicked.hasExtra(Intent.EXTRA_TEXT)){
            clickedItemIndex = intentStringClicked.getStringExtra(Intent.EXTRA_TEXT);
        }



        String valorReceb = clickedItemIndex;

        //    mCursor.moveToPosition(Integer.valueOf());

        //   String toastMessage = "Item #" + mCursor.getString(mCursor.getColumnIndex(AdvertiseContract.AnuncioEntrada.COLUNA_TITULO)) + " clicked.";

        Toast mToast;
        // mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        //  mToast.show();


        TextView tituloAnuncio = (TextView)findViewById(R.id.tv_title_advertisement);
        TextView horarAtendimento = (TextView)findViewById(R.id.tv_horafuncio_advertisement);
        TextView formasPagamento = (TextView)findViewById(R.id.tv_formas_pag_advertisement);
        TextView telContato = (TextView)findViewById(R.id.tv_tel_contato_advertisement);

    }

}
