package com.example.valdir.appitarare.data;

import android.provider.BaseColumns;

/**
 * Created by VALDIR on 09/07/2018.
 */

public class AnuncioContract{

    public static final class AnuncioEntrada implements BaseColumns{
        public static final String NOME_TABELA = "tabAnuncios";
        public static final String COLUNA_TITULO = "titulo";
        public static final String COLUNA_DESCRICAO = "descricao";
        public static final String COLUNA_HORA_ATEND = "horaAtendimento";
        public static final String COLUNA_FORMA_PAGAMENTO = "formaPagamento";
        public static final String COLUNA_TEL_CONTATO = "telContato";
        public static final String COLUNA_AVALIADO = "avaliado";
        public static final String COLUNA_IMG = "img";
        public static final String COLUNA_WIFI = "wifi";
        public static final String COLUNA_WHATSAPP = "whatsApp";
    }
}
