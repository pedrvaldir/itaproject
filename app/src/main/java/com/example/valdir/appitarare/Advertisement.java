package com.example.valdir.appitarare;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class Advertisement implements Parcelable {
    private String mId;
    private String mTitulo;
    private String mDescricao;
    private String mHorarAtendimento;
    private String mFormasPagamento;
    private String mTelContato;
    private int mAvaliado;
    private  int mImg;
    private int mWifi;
    private int mWhatsApp;

    public Advertisement(String id, String titulo, String descricao, String atendimento, String formasPagamento, String contato, int img, int avaliado, int    wifi, int whatsApp) {
        this.mId = id;
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

    public Advertisement(){

    }

    protected Advertisement(Parcel in) {
        mId = in.readString();
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

    public void setUid(String id){
        this.mId =  id;
    }

    public String getmId(){
        return mId;
    }

    public String getmTitulo() {
        return mTitulo;
    }

    public String getmDescricao() {
        return mDescricao;
    }

    public int getmAvaliado() {
        return mAvaliado;
    }

    public int getmImg() {
        return mImg;
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

    public int getmWhatsApp() {
        return mWhatsApp;
    }

    public int getmWifi() {
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
        parcel.writeString(mId);
        parcel.writeString(mTitulo);
        parcel.writeString(mDescricao);
        parcel.writeString(mHorarAtendimento);
        parcel.writeString(mFormasPagamento);
        parcel.writeString(mTelContato);
        parcel.writeInt(mImg);
        parcel.writeInt(mAvaliado);

    }

    public void setmTitulo(String mTitulo) {
        this.mTitulo = mTitulo;
    }

    public void setmDescricao(String mDescricao) {
        this.mDescricao = mDescricao;
    }

    public void setmHorarAtendimento(String mHorarAtendimento) {
        this.mHorarAtendimento = mHorarAtendimento;
    }

    public void setmFormasPagamento(String mFormasPagamento) {
        this.mFormasPagamento = mFormasPagamento;
    }

    public void setmTelContato(String mTelContato) {
        this.mTelContato = mTelContato;
    }

    public void setmAvaliado(int mAvaliado) {
        this.mAvaliado = mAvaliado;
    }

    public void setmImg(int mImg) {
        this.mImg = mImg;
    }

    public void setmWifi(int mWifi) {
        this.mWifi = mWifi;
    }

    public void setmWhatsApp(int mWhatsApp) {
        this.mWhatsApp = mWhatsApp;
    }
}
