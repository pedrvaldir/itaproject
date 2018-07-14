package com.example.valdir.appitarare;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class Advertisement implements Parcelable {
    private String mTitulo;
    private String mDescricao;
    private String mHorarAtendimento;
    private String mFormasPagamento;
    private String mTelContato;
    private int mAvaliado;
    private  int mImg;
    private Boolean mWifi;
    private Boolean mWhatsApp;


    public Advertisement(String titulo, String descricao, String atendimento, String formasPagamento, String contato, int img, int avaliado, Boolean wifi, Boolean whatsApp) {
        this.mTitulo = titulo;
        this.mDescricao = descricao;
        this.mHorarAtendimento = atendimento;
        this.mFormasPagamento = formasPagamento;
        this.mTelContato = contato;
        this.mImg = img;
        this.mAvaliado = avaliado;
        this.mWifi = wifi;
        this.mWhatsApp = whatsApp;
    }

    protected Advertisement(Parcel in) {
        mTitulo = in.readString();
        mDescricao = in.readString();
        mHorarAtendimento = in.readString();
        mFormasPagamento = in.readString();
        mTelContato = in.readString();
        mImg = in.readInt();
        mAvaliado = in.readInt();
    }

    public static final Creator<Advertisement> CREATOR = new Creator<Advertisement>() {
        @Override
        public Advertisement createFromParcel(Parcel in) {
            return new Advertisement(in);
        }

        @Override
        public Advertisement[] newArray(int size) {
            return new Advertisement[size];
        }
    };

    public String getTitulo() {
        return mTitulo;
    }

    public String getDescricao() {
        return mDescricao;
    }

    public int getImg() {
        return mImg;
    }

    public int getAvaliado(){
        return mAvaliado;}

    public String getmHorarAtendimento() {
        return mHorarAtendimento;
    }

    public String getmFormasPagamento() {
        return mFormasPagamento;
    }

    public String getmTelContato() {
        return mTelContato;
    }

    public Boolean getmWhatsApp() {
        return mWhatsApp;
    }

    public Boolean getmWifi() {
        return mWifi;
    }

    public String toString(){
        return mTitulo + "--" + mDescricao + "---";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitulo);
        parcel.writeString(mDescricao);
        parcel.writeString(mHorarAtendimento);
        parcel.writeString(mFormasPagamento);
        parcel.writeString(mTelContato);
        parcel.writeInt(mImg);
        parcel.writeInt(mAvaliado);

    }
}
