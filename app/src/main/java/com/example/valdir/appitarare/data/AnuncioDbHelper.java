package com.example.valdir.appitarare.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.valdir.appitarare.data.AnuncioContract.AnuncioEntrada;

/**
 * Created by VALDIR on 09/07/2018.
 */

public class AnuncioDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "anuncio.db";
    private static int DATABASE_VERSION = 1;

    public AnuncioDbHelper(Context context) {
        super(context, DATABASE_NAME, null,  DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_ANUNCIO_TABLE = "CREATE TABLE " +
                AnuncioEntrada.NOME_TABELA + " (" +
                AnuncioEntrada._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AnuncioEntrada.COLUNA_TITULO + " TEXT NOT NULL, " +
                AnuncioEntrada.COLUNA_DESCRICAO + " TEXT NOT NULL, " +
                AnuncioEntrada.COLUNA_HORA_ATEND + " TEXT, " +
                AnuncioEntrada.COLUNA_FORMA_PAGAMENTO + " TEXT NOT NULL, " +
                AnuncioEntrada.COLUNA_TEL_CONTATO + " TEXT NOT NULL, " +
                AnuncioEntrada.COLUNA_AVALIADO + " INTEGER, " +
                AnuncioEntrada.COLUNA_IMG + " INTEGER ," +
                AnuncioEntrada.COLUNA_WIFI + " INTEGER NOT NULL ," +
                AnuncioEntrada.COLUNA_WHATSAPP + " INTEGER NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_ANUNCIO_TABLE);
                }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AnuncioEntrada.NOME_TABELA);
        onCreate(sqLiteDatabase);

    }
}
