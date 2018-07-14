package com.example.valdir.appitarare.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.valdir.appitarare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VALDIR on 10/07/2018.
 */

public class TestUtil {

    public static int ANUN_WI_OK = 1;
    public static int ANUN_WTSAPP_OK = 1;
    public static int ANUN_WI= 0;
    public static int ANUN_WTSAPP = 0;

        public static void insertFakeData(SQLiteDatabase db){




        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_TITULO, "SUPERMERCADO JACKS");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_DESCRICAO, "O MELHOR LUGAR PARA VC E SUA FAMILIA");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_HORA_ATEND, "DAS 8H AS 18H, SEG A SEX");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_FORMA_PAGAMENTO, "CARTÕES: MASTERCARD, ELO, VISA");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_AVALIADO, 50);
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_TEL_CONTATO, "(15) 4567-9844");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_IMG, R.drawable.food);
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_WHATSAPP, ANUN_WTSAPP_OK);
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_WIFI, ANUN_WI);
        list.add(cv);

        cv = new ContentValues();
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_TITULO, "HOTEL FRYRED");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_DESCRICAO, "TODO MEDICAMENTO VC ENCONTRA AQUI");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_HORA_ATEND, "24H 7 VEZES POR SEMANA");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_FORMA_PAGAMENTO, "CARTÕES: MASTERCARD, ELO, VISA");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_AVALIADO, 10);
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_TEL_CONTATO, "(15) 4567-9844");
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_IMG, R.drawable.empresa1);
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_WHATSAPP, ANUN_WTSAPP_OK);
        cv.put(AdvertiseContract.AnuncioEntrada.COLUNA_WIFI, ANUN_WI);

        list.add(cv);


        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (AdvertiseContract.AnuncioEntrada.NOME_TABELA,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(AdvertiseContract.AnuncioEntrada.NOME_TABELA, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}